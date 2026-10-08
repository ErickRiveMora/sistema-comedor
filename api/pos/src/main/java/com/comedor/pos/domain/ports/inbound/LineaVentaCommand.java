package com.comedor.pos.domain.ports.inbound;

public record LineaVentaCommand(Long productoId, int cantidad) {
}
