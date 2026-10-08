package com.comedor.pos.domain.exception;

public class CajaCerradaException extends DomainException {

    public CajaCerradaException() {
        super("CAJA_CERRADA", "No hay una caja abierta. Debe abrir caja antes de procesar ventas o realizar el cierre.");
    }

    public CajaCerradaException(Long usuarioId) {
        super("CAJA_CERRADA", "El usuario " + usuarioId + " no tiene una caja abierta.");
    }
}
