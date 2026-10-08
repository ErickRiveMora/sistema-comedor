package com.comedor.pos.domain.ports.inbound;

import com.comedor.pos.domain.model.MetodoPago;

import java.util.List;

public record ProcesarVentaCommand(
        Long usuarioId,
        Long clienteId,
        MetodoPago metodoPago,
        List<LineaVentaCommand> lineas
) {
}
