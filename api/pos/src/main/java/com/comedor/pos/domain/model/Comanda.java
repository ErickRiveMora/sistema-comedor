package com.comedor.pos.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comanda {

    private Long id;
    private String folioTicket;
    private String folioCocina;
    private Long cierreCajaId;
    private Long usuarioId;
    private Long clienteId;
    private MetodoPago metodoPago;
    private BigDecimal total;
    private String codigoBarrasCobro;
    private LocalDateTime fecha;
    private EstadoComanda estado;
    private List<ComandaDetalle> detalles = new ArrayList<>();

    public Comanda() {
    }

    public static Comanda registrar(
            String folioTicket,
            String folioCocina,
            Long cierreCajaId,
            Long usuarioId,
            Long clienteId,
            MetodoPago metodoPago,
            List<ComandaDetalle> detalles,
            String codigoBarrasCobro,
            LocalDateTime fecha
    ) {
        Comanda comanda = new Comanda();
        comanda.folioTicket = folioTicket;
        comanda.folioCocina = folioCocina;
        comanda.cierreCajaId = cierreCajaId;
        comanda.usuarioId = usuarioId;
        comanda.clienteId = clienteId;
        comanda.metodoPago = metodoPago;
        comanda.detalles = detalles == null ? new ArrayList<>() : new ArrayList<>(detalles);
        comanda.total = comanda.detalles.stream()
                .map(ComandaDetalle::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
        comanda.codigoBarrasCobro = codigoBarrasCobro;
        comanda.fecha = fecha;
        comanda.estado = metodoPago == MetodoPago.FIADO_CREDITO ? EstadoComanda.FIADA : EstadoComanda.PAGADA;
        return comanda;
    }

    public boolean esFiado() {
        return metodoPago == MetodoPago.FIADO_CREDITO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolioTicket() {
        return folioTicket;
    }

    public void setFolioTicket(String folioTicket) {
        this.folioTicket = folioTicket;
    }

    public String getFolioCocina() {
        return folioCocina;
    }

    public void setFolioCocina(String folioCocina) {
        this.folioCocina = folioCocina;
    }

    public Long getCierreCajaId() {
        return cierreCajaId;
    }

    public void setCierreCajaId(Long cierreCajaId) {
        this.cierreCajaId = cierreCajaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCodigoBarrasCobro() {
        return codigoBarrasCobro;
    }

    public void setCodigoBarrasCobro(String codigoBarrasCobro) {
        this.codigoBarrasCobro = codigoBarrasCobro;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public List<ComandaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ComandaDetalle> detalles) {
        this.detalles = detalles == null ? new ArrayList<>() : detalles;
    }
}
