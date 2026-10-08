package com.comedor.pos.infrastructure.adapters.input.rest.dto;

import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.EstadoCaja;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CajaResponse(
        Long id,
        Long usuarioId,
        EstadoCaja estado,
        BigDecimal fondoInicial,
        LocalDateTime fechaApertura,
        LocalDateTime fechaCierre,
        BigDecimal totalEfectivo,
        BigDecimal totalTarjeta,
        BigDecimal totalTransferencia,
        BigDecimal totalFiado,
        BigDecimal totalEfectivoEsperado,
        BigDecimal efectivoDeclarado,
        BigDecimal diferenciaArqueo,
        BigDecimal gananciaNeta
) {

    public static CajaResponse from(CierreCaja caja) {
        return new CajaResponse(
                caja.getId(),
                caja.getUsuarioId(),
                caja.getEstado(),
                caja.getFondoInicial(),
                caja.getFechaApertura(),
                caja.getFechaCierre(),
                caja.getTotalEfectivo(),
                caja.getTotalTarjeta(),
                caja.getTotalTransferencia(),
                caja.getTotalFiado(),
                caja.getTotalEfectivoEsperado(),
                caja.getEfectivoDeclarado(),
                caja.getDiferenciaArqueo(),
                caja.getGananciaNeta()
        );
    }
}
