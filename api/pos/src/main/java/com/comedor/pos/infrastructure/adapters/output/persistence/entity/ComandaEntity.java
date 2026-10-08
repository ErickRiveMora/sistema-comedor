package com.comedor.pos.infrastructure.adapters.output.persistence.entity;

import com.comedor.pos.domain.model.EstadoComanda;
import com.comedor.pos.domain.model.MetodoPago;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comanda")
public class ComandaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "folio_ticket", nullable = false, unique = true, length = 40)
    private String folioTicket;

    @Column(name = "folio_cocina", nullable = false, unique = true, length = 40)
    private String folioCocina;

    @Column(name = "cierre_caja_id", nullable = false)
    private Long cierreCajaId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false, length = 30)
    private MetodoPago metodoPago;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal total;

    @Column(name = "codigo_barras_cobro", length = 80)
    private String codigoBarrasCobro;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoComanda estado;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComandaDetalleEntity> detalles = new ArrayList<>();

    public void reemplazarDetalles(List<ComandaDetalleEntity> nuevos) {
        detalles.clear();
        if (nuevos == null) {
            return;
        }
        for (ComandaDetalleEntity detalle : nuevos) {
            detalle.setComanda(this);
            detalles.add(detalle);
        }
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

    public List<ComandaDetalleEntity> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ComandaDetalleEntity> detalles) {
        this.detalles = detalles;
    }
}
