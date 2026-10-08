package com.comedor.pos.domain.service;

import com.comedor.pos.domain.exception.RecursoNoEncontradoException;
import com.comedor.pos.domain.exception.ValidacionNegocioException;
import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Cliente;
import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.model.ComandaDetalle;
import com.comedor.pos.domain.model.MetodoPago;
import com.comedor.pos.domain.model.Producto;
import com.comedor.pos.domain.ports.inbound.LineaVentaCommand;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VentaDomainService {

    public Comanda construirVenta(
            CierreCaja caja,
            Long usuarioId,
            Cliente cliente,
            MetodoPago metodoPago,
            List<LineaVentaCommand> lineas,
            List<Producto> productos,
            String folioTicket,
            String folioCocina,
            String codigoBarrasCobro,
            LocalDateTime ahora
    ) {
        if (caja == null) {
            throw new ValidacionNegocioException("No se puede vender sin una sesión de caja.");
        }
        caja.asegurarAbierta();
        if (metodoPago == null) {
            throw new ValidacionNegocioException("El método de pago es obligatorio.");
        }
        if (lineas == null || lineas.isEmpty()) {
            throw new ValidacionNegocioException("La venta debe incluir al menos un producto.");
        }

        Map<Long, Producto> productosPorId = productos.stream()
                .collect(Collectors.toMap(Producto::getId, Function.identity()));

        List<ComandaDetalle> detalles = new ArrayList<>();
        for (LineaVentaCommand linea : lineas) {
            if (linea.cantidad() <= 0) {
                throw new ValidacionNegocioException("La cantidad del producto " + linea.productoId() + " debe ser mayor a cero.");
            }
            Producto producto = productosPorId.get(linea.productoId());
            if (producto == null || !producto.isActivo()) {
                throw new RecursoNoEncontradoException("Producto", linea.productoId());
            }
            detalles.add(ComandaDetalle.deProducto(producto, linea.cantidad()));
        }

        Long clienteId = null;
        if (metodoPago == MetodoPago.FIADO_CREDITO) {
            if (cliente == null) {
                throw new ValidacionNegocioException("El fiado requiere un cliente con crédito autorizado.");
            }
            clienteId = cliente.getId();
        }

        Comanda comanda = Comanda.registrar(
                folioTicket,
                folioCocina,
                caja.getId(),
                usuarioId,
                clienteId,
                metodoPago,
                detalles,
                codigoBarrasCobro,
                ahora
        );

        if (comanda.esFiado()) {
            cliente.cargarFiado(comanda.getTotal());
        }
        caja.acumularVenta(comanda);
        return comanda;
    }
}
