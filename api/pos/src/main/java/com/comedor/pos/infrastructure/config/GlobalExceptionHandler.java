package com.comedor.pos.infrastructure.config;

import com.comedor.pos.domain.exception.CajaCerradaException;
import com.comedor.pos.domain.exception.CajaYaAbiertaException;
import com.comedor.pos.domain.exception.CreditoInsuficienteException;
import com.comedor.pos.domain.exception.DomainException;
import com.comedor.pos.domain.exception.RecursoNoEncontradoException;
import com.comedor.pos.domain.exception.ValidacionNegocioException;
import com.comedor.pos.infrastructure.adapters.input.rest.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CajaCerradaException.class)
    public ResponseEntity<ErrorResponse> cajaCerrada(CajaCerradaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.of(ex.getCodigo(), ex.getMessage()));
    }

    @ExceptionHandler(CajaYaAbiertaException.class)
    public ResponseEntity<ErrorResponse> cajaYaAbierta(CajaYaAbiertaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.of(ex.getCodigo(), ex.getMessage()));
    }

    @ExceptionHandler(CreditoInsuficienteException.class)
    public ResponseEntity<ErrorResponse> creditoInsuficiente(CreditoInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ErrorResponse.of(ex.getCodigo(), ex.getMessage()));
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> noEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(ex.getCodigo(), ex.getMessage()));
    }

    @ExceptionHandler(ValidacionNegocioException.class)
    public ResponseEntity<ErrorResponse> validacion(ValidacionNegocioException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(ex.getCodigo(), ex.getMessage()));
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> dominio(DomainException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ErrorResponse.of(ex.getCodigo(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> generica(Exception ex) {
        log.error("Error no controlado", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of("ERROR_INTERNO", "Ocurrió un error inesperado al procesar la solicitud."));
    }
}
