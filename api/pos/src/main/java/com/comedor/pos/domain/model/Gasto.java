package com.comedor.pos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Gasto {

    private Long id;
    private String concepto;
    private BigDecimal monto;
    private String categoriaGasto;
    private Long usuarioId;
    private LocalDateTime fecha;

    public Gasto() {
    }

    public Gasto(Long id, String concepto, BigDecimal monto, String categoriaGasto, Long usuarioId, LocalDateTime fecha) {
        this.id = id;
        this.concepto = concepto;
        this.monto = monto;
        this.categoriaGasto = categoriaGasto;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getCategoriaGasto() {
        return categoriaGasto;
    }

    public void setCategoriaGasto(String categoriaGasto) {
        this.categoriaGasto = categoriaGasto;
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
