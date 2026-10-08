package com.comedor.pos.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "receta")
public class RecetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(name = "insumo_id", nullable = false)
    private Long insumoId;

    @Column(name = "cantidad_por_porcion", nullable = false, precision = 14, scale = 3)
    private BigDecimal cantidadPorPorcion;

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
