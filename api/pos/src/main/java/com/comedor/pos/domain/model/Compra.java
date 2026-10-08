package com.comedor.pos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Compra {

    private Long id;
    private Long insumoId;
    private BigDecimal cantidad;
    private BigDecimal costoTotal;
    private String proveedor;
    private Long usuarioId;
    private LocalDateTime fecha;

    public Compra() {
    }

    public Compra(Long id, Long insumoId, BigDecimal cantidad, BigDecimal costoTotal, String proveedor, Long usuarioId, LocalDateTime fecha) {
        this.id = id;
        this.insumoId = insumoId;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
        this.proveedor = proveedor;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInsumoId() {
        return insumoId;
    }

    public void setInsumoId(Long insumoId) {
        this.insumoId = insumoId;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
