package com.comedor.pos.domain.exception;

public class CajaYaAbiertaException extends DomainException {

    public CajaYaAbiertaException(Long usuarioId) {
        super("CAJA_YA_ABIERTA", "El usuario " + usuarioId + " ya tiene una caja abierta. Debe cerrarla antes de abrir otra.");
    }
}
