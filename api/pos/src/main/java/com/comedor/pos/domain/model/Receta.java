package com.comedor.pos.domain.model;

import java.math.BigDecimal;

public class Receta {

    private Long id;
    private Long productoId;
    private Long insumoId;
    private BigDecimal cantidadPorPorcion;

    public Receta() {
    }

    public Receta(Long id, Long productoId, Long insumoId, BigDecimal cantidadPorPorcion) {
        this.id = id;
        this.productoId = productoId;
        this.insumoId = insumoId;
        this.cantidadPorPorcion = cantidadPorPorcion;
    }

    public BigDecimal consumoPara(int cantidadVendida) {
        BigDecimal porcion = cantidadPorPorcion == null ? BigDecimal.ZERO : cantidadPorPorcion;
        return porcion.multiply(BigDecimal.valueOf(cantidadVendida));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getInsumoId() {
        return insumoId;
    }

    public void setInsumoId(Long insumoId) {
        this.insumoId = insumoId;
    }

    public BigDecimal getCantidadPorPorcion() {
        return cantidadPorPorcion;
    }

    public void setCantidadPorPorcion(BigDecimal cantidadPorPorcion) {
        this.cantidadPorPorcion = cantidadPorPorcion;
    }
}
