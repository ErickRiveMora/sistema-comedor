package com.comedor.pos.domain.model;

import java.math.BigDecimal;

public class Empleado {

    private Long id;
    private String nombre;
    private String puesto;
    private BigDecimal sueldoBase;
    private Long usuarioId;
    private boolean activo;

    public Empleado() {
    }

    public Empleado(Long id, String nombre, String puesto, BigDecimal sueldoBase, Long usuarioId, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.sueldoBase = sueldoBase;
        this.usuarioId = usuarioId;
        this.activo = activo;
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
