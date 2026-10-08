package com.comedor.pos.infrastructure.adapters.input.rest.dto;

import java.math.BigDecimal;

public record CerrarCajaRequest(Long usuarioId, BigDecimal efectivoDeclarado) {
}
