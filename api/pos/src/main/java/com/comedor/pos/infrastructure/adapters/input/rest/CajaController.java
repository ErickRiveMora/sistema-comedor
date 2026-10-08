package com.comedor.pos.infrastructure.adapters.input.rest;

import com.comedor.pos.domain.ports.inbound.AbrirCajaCommand;
import com.comedor.pos.domain.ports.inbound.AbrirCajaUseCase;
import com.comedor.pos.domain.ports.inbound.CerrarCajaCommand;
import com.comedor.pos.domain.ports.inbound.CerrarCajaUseCase;
import com.comedor.pos.infrastructure.adapters.input.rest.dto.AbrirCajaRequest;
import com.comedor.pos.infrastructure.adapters.input.rest.dto.CajaResponse;
import com.comedor.pos.infrastructure.adapters.input.rest.dto.CerrarCajaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/caja")
public class CajaController {

    private final AbrirCajaUseCase abrirCajaUseCase;
    private final CerrarCajaUseCase cerrarCajaUseCase;

    public CajaController(AbrirCajaUseCase abrirCajaUseCase, CerrarCajaUseCase cerrarCajaUseCase) {
        this.abrirCajaUseCase = abrirCajaUseCase;
        this.cerrarCajaUseCase = cerrarCajaUseCase;
    }

    @PostMapping("/abrir")
    @ResponseStatus(HttpStatus.CREATED)
    public CajaResponse abrir(@RequestBody AbrirCajaRequest request) {
        return CajaResponse.from(abrirCajaUseCase.ejecutar(
                new AbrirCajaCommand(request.usuarioId(), request.fondoInicial())
        ));
    }

    @PostMapping("/cierre")
    public CajaResponse cerrar(@RequestBody CerrarCajaRequest request) {
        return CajaResponse.from(cerrarCajaUseCase.ejecutar(
                new CerrarCajaCommand(request.usuarioId(), request.efectivoDeclarado())
        ));
    }
}
