package com.comedor.pos.infrastructure.adapters.input.rest.dto;

import java.math.BigDecimal;

public record InsumoAlertaResponse(
        Long insumoId,
        String nombre,
        BigDecimal stockActual,
        BigDecimal stockMinimo,
        String unidad
) {
}
