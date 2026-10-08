package com.comedor.pos.infrastructure.adapters.input.usecase;

import com.comedor.pos.domain.exception.CajaCerradaException;
import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.ports.inbound.CerrarCajaCommand;
import com.comedor.pos.domain.ports.inbound.CerrarCajaUseCase;
import com.comedor.pos.domain.ports.outbound.CierreCajaPort;
import com.comedor.pos.domain.ports.outbound.ComandaPort;
import com.comedor.pos.domain.ports.outbound.ImpresoraPort;
import com.comedor.pos.domain.service.CajaDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CerrarCajaService implements CerrarCajaUseCase {

    private static final ZoneId ZONA = ZoneId.of("America/Mexico_City");

    private final CierreCajaPort cierreCajaPort;
    private final ComandaPort comandaPort;
    private final ImpresoraPort impresoraPort;
    private final CajaDomainService cajaDomainService;

    public CerrarCajaService(
            CierreCajaPort cierreCajaPort,
            ComandaPort comandaPort,
            ImpresoraPort impresoraPort,
            CajaDomainService cajaDomainService
    ) {
        this.cierreCajaPort = cierreCajaPort;
        this.comandaPort = comandaPort;
        this.impresoraPort = impresoraPort;
        this.cajaDomainService = cajaDomainService;
    }

    @Override
    @Transactional
    public CierreCaja ejecutar(CerrarCajaCommand command) {
        CierreCaja caja = cierreCajaPort.buscarAbiertaPorUsuario(command.usuarioId())
                .orElseThrow(() -> new CajaCerradaException(command.usuarioId()));
        List<Comanda> ventas = comandaPort.listarPorCierreCaja(caja.getId());
        CierreCaja cerrada = cajaDomainService.cerrar(
                caja,
                ventas,
                command.efectivoDeclarado(),
                LocalDateTime.now(ZONA)
        );
        CierreCaja persistida = cierreCajaPort.guardar(cerrada);
        impresoraPort.imprimirTicketZ(persistida);
        return persistida;
    }
}
