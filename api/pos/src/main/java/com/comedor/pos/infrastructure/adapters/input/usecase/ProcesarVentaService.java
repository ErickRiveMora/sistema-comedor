package com.comedor.pos.infrastructure.adapters.input.usecase;

import com.comedor.pos.domain.exception.CajaCerradaException;
import com.comedor.pos.domain.exception.RecursoNoEncontradoException;
import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Cliente;
import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.model.Insumo;
import com.comedor.pos.domain.model.MetodoPago;
import com.comedor.pos.domain.model.Producto;
import com.comedor.pos.domain.model.Receta;
import com.comedor.pos.domain.ports.inbound.LineaVentaCommand;
import com.comedor.pos.domain.ports.inbound.ProcesarVentaCommand;
import com.comedor.pos.domain.ports.inbound.ProcesarVentaUseCase;
import com.comedor.pos.domain.ports.inbound.ResultadoVenta;
import com.comedor.pos.domain.ports.outbound.CierreCajaPort;
import com.comedor.pos.domain.ports.outbound.ClientePort;
import com.comedor.pos.domain.ports.outbound.ComandaPort;
import com.comedor.pos.domain.ports.outbound.ImpresoraPort;
import com.comedor.pos.domain.ports.outbound.InsumoPort;
import com.comedor.pos.domain.ports.outbound.NotificacionPort;
import com.comedor.pos.domain.ports.outbound.ProductoPort;
import com.comedor.pos.domain.ports.outbound.RecetaPort;
import com.comedor.pos.domain.service.FolioDomainService;
import com.comedor.pos.domain.service.InventarioDomainService;
import com.comedor.pos.domain.service.InventarioDomainService.ResultadoDescuentoStock;
import com.comedor.pos.domain.service.VentaDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcesarVentaService implements ProcesarVentaUseCase {

    private static final ZoneId ZONA = ZoneId.of("America/Mexico_City");

    private final CierreCajaPort cierreCajaPort;
    private final ProductoPort productoPort;
    private final ClientePort clientePort;
    private final RecetaPort recetaPort;
    private final InsumoPort insumoPort;
    private final ComandaPort comandaPort;
    private final ImpresoraPort impresoraPort;
    private final NotificacionPort notificacionPort;
    private final VentaDomainService ventaDomainService;
    private final InventarioDomainService inventarioDomainService;
    private final FolioDomainService folioDomainService;

    public ProcesarVentaService(
            CierreCajaPort cierreCajaPort,
            ProductoPort productoPort,
            ClientePort clientePort,
            RecetaPort recetaPort,
            InsumoPort insumoPort,
            ComandaPort comandaPort,
            ImpresoraPort impresoraPort,
            NotificacionPort notificacionPort,
            VentaDomainService ventaDomainService,
            InventarioDomainService inventarioDomainService,
            FolioDomainService folioDomainService
    ) {
        this.cierreCajaPort = cierreCajaPort;
        this.productoPort = productoPort;
        this.clientePort = clientePort;
        this.recetaPort = recetaPort;
        this.insumoPort = insumoPort;
        this.comandaPort = comandaPort;
        this.impresoraPort = impresoraPort;
        this.notificacionPort = notificacionPort;
        this.ventaDomainService = ventaDomainService;
        this.inventarioDomainService = inventarioDomainService;
        this.folioDomainService = folioDomainService;
    }

    @Override
    @Transactional
    public ResultadoVenta ejecutar(ProcesarVentaCommand command) {
        CierreCaja caja = cierreCajaPort.buscarAbiertaPorUsuario(command.usuarioId())
                .orElseThrow(() -> new CajaCerradaException(command.usuarioId()));

        List<Long> productoIds = command.lineas().stream().map(LineaVentaCommand::productoId).toList();
        List<Producto> productos = productoPort.buscarPorIds(productoIds);

        Cliente cliente = null;
        if (command.metodoPago() == MetodoPago.FIADO_CREDITO) {
            if (command.clienteId() == null) {
                throw new RecursoNoEncontradoException("Cliente", "requerido para FIADO_CREDITO");
            }
            cliente = clientePort.buscarPorId(command.clienteId())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Cliente", command.clienteId()));
        }

        LocalDateTime ahora = LocalDateTime.now(ZONA);
        LocalDate hoy = ahora.toLocalDate();
        long secuencia = comandaPort.contarFoliosDelDia(hoy) + 1;
        String folioTicket = folioDomainService.folioTicket(hoy, secuencia);
        String folioCocina = folioDomainService.folioCocina(hoy, secuencia);
        String codigoBarras = command.metodoPago() == MetodoPago.FIADO_CREDITO
                ? folioDomainService.codigoBarrasFiado(folioTicket)
                : null;

        Comanda comanda = ventaDomainService.construirVenta(
                caja,
                command.usuarioId(),
                cliente,
                command.metodoPago(),
                command.lineas(),
                productos,
                folioTicket,
                folioCocina,
                codigoBarras,
                ahora
        );

        List<Receta> recetas = recetaPort.buscarPorProductoIds(productoIds);
        List<Long> insumoIds = recetas.stream().map(Receta::getInsumoId).distinct().collect(Collectors.toList());
        List<Insumo> insumos = insumoPort.buscarPorIds(insumoIds);
        ResultadoDescuentoStock stock = inventarioDomainService.descontarPorReceta(comanda.getDetalles(), recetas, insumos);

        if (!stock.insumosActualizados().isEmpty()) {
            insumoPort.guardarTodos(stock.insumosActualizados());
        }
        if (cliente != null) {
            clientePort.guardar(cliente);
        }
        cierreCajaPort.guardar(caja);
        Comanda persistida = comandaPort.guardar(comanda);

        impresoraPort.imprimirVentaDual(persistida);
        if (!stock.insumosEnAlerta().isEmpty()) {
            notificacionPort.notificarReabastecimiento(stock.insumosEnAlerta());
        }
        return new ResultadoVenta(persistida, !stock.insumosEnAlerta().isEmpty(), stock.insumosEnAlerta());
    }
}
