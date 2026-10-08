package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.CierreCaja;

import java.util.Optional;

public interface CierreCajaPort {

    CierreCaja guardar(CierreCaja cierreCaja);

    Optional<CierreCaja> buscarPorId(Long id);

    Optional<CierreCaja> buscarAbiertaPorUsuario(Long usuarioId);
}
