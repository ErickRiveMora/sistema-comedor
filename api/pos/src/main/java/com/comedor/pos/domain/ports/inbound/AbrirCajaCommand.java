package com.comedor.pos.domain.ports.inbound;

import java.math.BigDecimal;

public record AbrirCajaCommand(Long usuarioId, BigDecimal fondoInicial) {
}
