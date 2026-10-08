package com.comedor.pos.domain.exception;

public class RecursoNoEncontradoException extends DomainException {

    public RecursoNoEncontradoException(String recurso, Object id) {
        super("RECURSO_NO_ENCONTRADO", recurso + " no encontrado: " + id);
    }
}
