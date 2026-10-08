package com.comedor.pos.domain.model;

import com.comedor.pos.domain.exception.ValidacionNegocioException;

import java.math.BigDecimal;

public class Insumo {

    private Long id;
    private String nombre;
    private String unidad;
    private BigDecimal stockActual;
    private BigDecimal stockMinimo;
    private BigDecimal costoUnitario;

    public Insumo() {
    }

    public Insumo(Long id, String nombre, String unidad, BigDecimal stockActual, BigDecimal stockMinimo, BigDecimal costoUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.costoUnitario = costoUnitario;
    }

    public void descontar(BigDecimal cantidad) {
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidacionNegocioException("La cantidad a descontar del insumo debe ser mayor a cero.");
        }
        BigDecimal actual = stockActual == null ? BigDecimal.ZERO : stockActual;
        this.stockActual = actual.subtract(cantidad);
    }

    public boolean estaEnAlertaStockBajo() {
        BigDecimal actual = stockActual == null ? BigDecimal.ZERO : stockActual;
        BigDecimal minimo = stockMinimo == null ? BigDecimal.ZERO : stockMinimo;
        return actual.compareTo(minimo) <= 0;
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

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public BigDecimal getStockActual() {
        return stockActual;
    }

    public void setStockActual(BigDecimal stockActual) {
        this.stockActual = stockActual;
    }

    public BigDecimal getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(BigDecimal stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
}
