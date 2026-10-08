package com.comedor.pos.infrastructure.adapters.input.rest.dto;

import java.math.BigDecimal;

public record AbrirCajaRequest(Long usuarioId, BigDecimal fondoInicial) {
}
