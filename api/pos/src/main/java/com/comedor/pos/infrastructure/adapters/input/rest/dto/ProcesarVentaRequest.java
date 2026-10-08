package com.comedor.pos.infrastructure.adapters.input.rest.dto;

import com.comedor.pos.domain.model.MetodoPago;

import java.util.List;

public record ProcesarVentaRequest(
        Long usuarioId,
        Long clienteId,
        MetodoPago metodoPago,
        List<LineaVentaRequest> items
) {
}
