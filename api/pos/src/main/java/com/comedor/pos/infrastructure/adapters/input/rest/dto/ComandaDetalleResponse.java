package com.comedor.pos.infrastructure.adapters.input.rest.dto;

import java.math.BigDecimal;

public record ComandaDetalleResponse(
        Long productoId,
        String descripcion,
        int cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal
) {
}
