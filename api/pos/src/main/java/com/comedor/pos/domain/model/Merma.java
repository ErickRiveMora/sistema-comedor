package com.comedor.pos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Merma {

    private Long id;
    private Long insumoId;
    private BigDecimal cantidad;
    private String motivo;
    private Long usuarioId;
    private LocalDateTime fecha;

    public Merma() {
    }

    public Merma(Long id, Long insumoId, BigDecimal cantidad, String motivo, Long usuarioId, LocalDateTime fecha) {
        this.id = id;
        this.insumoId = insumoId;
        this.cantidad = cantidad;
        this.motivo = motivo;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
