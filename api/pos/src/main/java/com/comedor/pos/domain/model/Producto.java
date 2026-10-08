package com.comedor.pos.domain.model;

import java.math.BigDecimal;

public class Producto {

    private Long id;
    private Long categoriaId;
    private String nombre;
    private BigDecimal precioVenta;
    private String codigoAtajo;
    private boolean activo;

    public Producto() {
    }

    public Producto(Long id, Long categoriaId, String nombre, BigDecimal precioVenta, String codigoAtajo, boolean activo) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.codigoAtajo = codigoAtajo;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getCodigoAtajo() {
        return codigoAtajo;
    }

    public void setCodigoAtajo(String codigoAtajo) {
        this.codigoAtajo = codigoAtajo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
