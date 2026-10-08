package com.comedor.pos.infrastructure.adapters.input.usecase;

import com.comedor.pos.domain.exception.RecursoNoEncontradoException;
import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Usuario;
import com.comedor.pos.domain.ports.inbound.AbrirCajaCommand;
import com.comedor.pos.domain.ports.inbound.AbrirCajaUseCase;
import com.comedor.pos.domain.ports.outbound.CierreCajaPort;
import com.comedor.pos.domain.ports.outbound.UsuarioPort;
import com.comedor.pos.domain.service.CajaDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AbrirCajaService implements AbrirCajaUseCase {

    private static final ZoneId ZONA = ZoneId.of("America/Mexico_City");

    private final UsuarioPort usuarioPort;
    private final CierreCajaPort cierreCajaPort;
    private final CajaDomainService cajaDomainService;

    public AbrirCajaService(UsuarioPort usuarioPort, CierreCajaPort cierreCajaPort, CajaDomainService cajaDomainService) {
        this.usuarioPort = usuarioPort;
        this.cierreCajaPort = cierreCajaPort;
        this.cajaDomainService = cajaDomainService;
    }

    @Override
    @Transactional
    public CierreCaja ejecutar(AbrirCajaCommand command) {
        Usuario usuario = usuarioPort.buscarPorId(command.usuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", command.usuarioId()));
        CierreCaja abierta = cajaDomainService.abrir(
                usuario,
                command.fondoInicial(),
                cierreCajaPort.buscarAbiertaPorUsuario(usuario.getId()),
                LocalDateTime.now(ZONA)
        );
        return cierreCajaPort.guardar(abierta);
    }
}
