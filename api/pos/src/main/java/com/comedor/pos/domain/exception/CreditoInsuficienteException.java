package com.comedor.pos.domain.exception;

import java.math.BigDecimal;

public class CreditoInsuficienteException extends DomainException {

    public CreditoInsuficienteException(BigDecimal limite, BigDecimal saldo, BigDecimal venta) {
        super(
                "CREDITO_INSUFICIENTE",
                "El crédito del cliente es insuficiente. Límite: " + limite
                        + ", saldo pendiente: " + saldo
                        + ", venta: " + venta
                        + ". Disponible: " + limite.subtract(saldo)
        );
    }
}
