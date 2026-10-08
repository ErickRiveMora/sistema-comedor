package com.comedor.pos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NominaMovimiento {

    private Long id;
    private Long empleadoId;
    private TipoNominaMovimiento tipo;
    private BigDecimal monto;
    private LocalDateTime fecha;
    private Long referenciaComandaId;
    private String concepto;

    public NominaMovimiento() {
    }

    public NominaMovimiento(
            Long id,
            Long empleadoId,
            TipoNominaMovimiento tipo,
            BigDecimal monto,
            LocalDateTime fecha,
            Long referenciaComandaId,
            String concepto
    ) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        this.referenciaComandaId = referenciaComandaId;
        this.concepto = concepto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public TipoNominaMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoNominaMovimiento tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Long getReferenciaComandaId() {
        return referenciaComandaId;
    }

    public void setReferenciaComandaId(Long referenciaComandaId) {
        this.referenciaComandaId = referenciaComandaId;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
