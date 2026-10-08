package com.comedor.pos.infrastructure.adapters.output.persistence.entity;

import com.comedor.pos.domain.model.EstadoCaja;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cierre_caja")
public class CierreCajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "fondo_inicial", nullable = false, precision = 14, scale = 2)
    private BigDecimal fondoInicial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCaja estado;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "efectivo_declarado", precision = 14, scale = 2)
    private BigDecimal efectivoDeclarado;

    @Column(name = "total_efectivo_esperado", precision = 14, scale = 2)
    private BigDecimal totalEfectivoEsperado;

    @Column(name = "diferencia_arqueo", precision = 14, scale = 2)
    private BigDecimal diferenciaArqueo;

    @Column(name = "total_efectivo", precision = 14, scale = 2)
    private BigDecimal totalEfectivo;

    @Column(name = "total_tarjeta", precision = 14, scale = 2)
    private BigDecimal totalTarjeta;

    @Column(name = "total_transferencia", precision = 14, scale = 2)
    private BigDecimal totalTransferencia;

    @Column(name = "total_fiado", precision = 14, scale = 2)
    private BigDecimal totalFiado;

    @Column(name = "ganancia_neta", precision = 14, scale = 2)
    private BigDecimal gananciaNeta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BigDecimal getFondoInicial() {
        return fondoInicial;
    }

    public void setFondoInicial(BigDecimal fondoInicial) {
        this.fondoInicial = fondoInicial;
    }

    public EstadoCaja getEstado() {
        return estado;
    }

    public void setEstado(EstadoCaja estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public BigDecimal getEfectivoDeclarado() {
        return efectivoDeclarado;
    }

    public void setEfectivoDeclarado(BigDecimal efectivoDeclarado) {
        this.efectivoDeclarado = efectivoDeclarado;
    }

    public BigDecimal getTotalEfectivoEsperado() {
        return totalEfectivoEsperado;
    }

    public void setTotalEfectivoEsperado(BigDecimal totalEfectivoEsperado) {
        this.totalEfectivoEsperado = totalEfectivoEsperado;
    }

    public BigDecimal getDiferenciaArqueo() {
        return diferenciaArqueo;
    }

    public void setDiferenciaArqueo(BigDecimal diferenciaArqueo) {
        this.diferenciaArqueo = diferenciaArqueo;
    }

    public BigDecimal getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(BigDecimal totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public BigDecimal getTotalTarjeta() {
        return totalTarjeta;
    }

    public void setTotalTarjeta(BigDecimal totalTarjeta) {
        this.totalTarjeta = totalTarjeta;
    }

    public BigDecimal getTotalTransferencia() {
        return totalTransferencia;
    }

    public void setTotalTransferencia(BigDecimal totalTransferencia) {
        this.totalTransferencia = totalTransferencia;
    }

    public BigDecimal getTotalFiado() {
        return totalFiado;
    }

    public void setTotalFiado(BigDecimal totalFiado) {
        this.totalFiado = totalFiado;
    }

    public BigDecimal getGananciaNeta() {
        return gananciaNeta;
    }

    public void setGananciaNeta(BigDecimal gananciaNeta) {
        this.gananciaNeta = gananciaNeta;
    }
}
