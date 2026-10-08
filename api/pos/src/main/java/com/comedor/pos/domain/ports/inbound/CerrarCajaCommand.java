package com.comedor.pos.domain.ports.inbound;

import java.math.BigDecimal;

public record CerrarCajaCommand(Long usuarioId, BigDecimal efectivoDeclarado) {
}
