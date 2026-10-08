package com.comedor.pos.domain.model;

import com.comedor.pos.domain.exception.CreditoInsuficienteException;
import com.comedor.pos.domain.exception.ValidacionNegocioException;

import java.math.BigDecimal;

public class Cliente {

    private Long id;
    private String nombre;
    private String telefono;
    private BigDecimal limiteCredito;
    private BigDecimal saldoPendiente;
    private boolean activo;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String telefono, BigDecimal limiteCredito, BigDecimal saldoPendiente, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.limiteCredito = limiteCredito;
        this.saldoPendiente = saldoPendiente;
        this.activo = activo;
    }

    public void cargarFiado(BigDecimal totalVenta) {
        if (!activo) {
            throw new ValidacionNegocioException("El cliente no está activo para fiado.");
        }
        BigDecimal limite = nvl(limiteCredito);
        BigDecimal saldo = nvl(saldoPendiente);
        BigDecimal venta = nvl(totalVenta);
        if (saldo.add(venta).compareTo(limite) > 0) {
            throw new CreditoInsuficienteException(limite, saldo, venta);
        }
        this.saldoPendiente = saldo.add(venta);
    }

    public BigDecimal creditoDisponible() {
        return nvl(limiteCredito).subtract(nvl(saldoPendiente));
    }

    private static BigDecimal nvl(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(BigDecimal limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public BigDecimal getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(BigDecimal saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
