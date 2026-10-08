package com.comedor.pos.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComandaDetalle {

    private Long id;
    private Long comandaId;
    private Long productoId;
    private String descripcion;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public ComandaDetalle() {
    }

    public static ComandaDetalle deProducto(Producto producto, int cantidad) {
        ComandaDetalle detalle = new ComandaDetalle();
        detalle.productoId = producto.getId();
        detalle.descripcion = producto.getNombre();
        detalle.cantidad = cantidad;
        detalle.precioUnitario = producto.getPrecioVenta().setScale(2, RoundingMode.HALF_UP);
        detalle.subtotal = detalle.precioUnitario.multiply(BigDecimal.valueOf(cantidad)).setScale(2, RoundingMode.HALF_UP);
        return detalle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComandaId() {
        return comandaId;
    }

    public void setComandaId(Long comandaId) {
        this.comandaId = comandaId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
