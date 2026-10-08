package com.comedor.pos.domain.model;

import com.comedor.pos.domain.exception.CajaCerradaException;
import com.comedor.pos.domain.exception.ValidacionNegocioException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class CierreCaja {

    private Long id;
    private Long usuarioId;
    private BigDecimal fondoInicial;
    private EstadoCaja estado;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;
    private BigDecimal efectivoDeclarado;
    private BigDecimal totalEfectivoEsperado;
    private BigDecimal diferenciaArqueo;
    private BigDecimal totalEfectivo;
    private BigDecimal totalTarjeta;
    private BigDecimal totalTransferencia;
    private BigDecimal totalFiado;
    private BigDecimal gananciaNeta;

    public CierreCaja() {
    }

    public static CierreCaja abrir(Long usuarioId, BigDecimal fondoInicial, LocalDateTime ahora) {
        if (fondoInicial == null || fondoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidacionNegocioException("El fondo inicial no puede ser negativo.");
        }
        CierreCaja caja = new CierreCaja();
        caja.usuarioId = usuarioId;
        caja.fondoInicial = fondoInicial.setScale(2, RoundingMode.HALF_UP);
        caja.estado = EstadoCaja.ABIERTA;
        caja.fechaApertura = ahora;
        caja.totalEfectivo = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        caja.totalTarjeta = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        caja.totalTransferencia = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        caja.totalFiado = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        return caja;
    }

    public void asegurarAbierta() {
        if (estado != EstadoCaja.ABIERTA) {
            throw new CajaCerradaException(usuarioId);
        }
    }

    public void acumularVenta(Comanda comanda) {
        asegurarAbierta();
        BigDecimal monto = nvl(comanda.getTotal());
        switch (comanda.getMetodoPago()) {
            case EFECTIVO -> totalEfectivo = nvl(totalEfectivo).add(monto);
            case TARJETA -> totalTarjeta = nvl(totalTarjeta).add(monto);
            case TRANSFERENCIA -> totalTransferencia = nvl(totalTransferencia).add(monto);
            case FIADO_CREDITO -> totalFiado = nvl(totalFiado).add(monto);
        }
    }

    public void cerrarConArqueo(List<Comanda> ventasTurno, BigDecimal efectivoDeclarado, LocalDateTime ahora) {
        asegurarAbierta();
        if (efectivoDeclarado == null || efectivoDeclarado.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidacionNegocioException("El efectivo declarado no puede ser negativo.");
        }
        recalcularDesglose(ventasTurno);
        this.efectivoDeclarado = efectivoDeclarado.setScale(2, RoundingMode.HALF_UP);
        this.totalEfectivoEsperado = nvl(fondoInicial).add(nvl(totalEfectivo)).setScale(2, RoundingMode.HALF_UP);
        this.diferenciaArqueo = this.efectivoDeclarado.subtract(this.totalEfectivoEsperado).setScale(2, RoundingMode.HALF_UP);
        this.gananciaNeta = nvl(totalEfectivo)
                .add(nvl(totalTarjeta))
                .add(nvl(totalTransferencia))
                .setScale(2, RoundingMode.HALF_UP);
        this.estado = EstadoCaja.CERRADA;
        this.fechaCierre = ahora;
    }

    private void recalcularDesglose(List<Comanda> ventasTurno) {
        totalEfectivo = BigDecimal.ZERO;
        totalTarjeta = BigDecimal.ZERO;
        totalTransferencia = BigDecimal.ZERO;
        totalFiado = BigDecimal.ZERO;
        if (ventasTurno == null) {
            return;
        }
        for (Comanda venta : ventasTurno) {
            if (venta.getEstado() == EstadoComanda.ANULADA) {
                continue;
            }
            BigDecimal monto = nvl(venta.getTotal());
            switch (venta.getMetodoPago()) {
                case EFECTIVO -> totalEfectivo = totalEfectivo.add(monto);
                case TARJETA -> totalTarjeta = totalTarjeta.add(monto);
                case TRANSFERENCIA -> totalTransferencia = totalTransferencia.add(monto);
                case FIADO_CREDITO -> totalFiado = totalFiado.add(monto);
            }
        }
        totalEfectivo = totalEfectivo.setScale(2, RoundingMode.HALF_UP);
        totalTarjeta = totalTarjeta.setScale(2, RoundingMode.HALF_UP);
        totalTransferencia = totalTransferencia.setScale(2, RoundingMode.HALF_UP);
        totalFiado = totalFiado.setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal nvl(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

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
