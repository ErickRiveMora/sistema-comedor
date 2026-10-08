package com.comedor.pos.infrastructure.adapters.input.rest;

import com.comedor.pos.domain.ports.inbound.LineaVentaCommand;
import com.comedor.pos.domain.ports.inbound.ProcesarVentaCommand;
import com.comedor.pos.domain.ports.inbound.ProcesarVentaUseCase;
import com.comedor.pos.infrastructure.adapters.input.rest.dto.ProcesarVentaRequest;
import com.comedor.pos.infrastructure.adapters.input.rest.dto.VentaResponse;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    private final ProcesarVentaUseCase procesarVentaUseCase;

    public VentaController(ProcesarVentaUseCase procesarVentaUseCase) {
        this.procesarVentaUseCase = procesarVentaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponse vender(@RequestBody ProcesarVentaRequest request) {
        List<LineaVentaCommand> lineas = request.items() == null
                ? List.of()
                : request.items().stream()
                .map(item -> new LineaVentaCommand(item.productoId(), item.cantidad()))
                .toList();
        return VentaResponse.from(procesarVentaUseCase.ejecutar(
                new ProcesarVentaCommand(request.usuarioId(), request.clienteId(), request.metodoPago(), lineas)
        ));
    }
}
