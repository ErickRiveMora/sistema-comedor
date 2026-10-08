package com.comedor.pos.domain.exception;

public class ValidacionNegocioException extends DomainException {

    public ValidacionNegocioException(String mensaje) {
        super("VALIDACION_NEGOCIO", mensaje);
    }
}
