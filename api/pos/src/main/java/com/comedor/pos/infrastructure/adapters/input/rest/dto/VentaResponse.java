package com.comedor.pos.infrastructure.adapters.input.rest.dto;

import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.model.ComandaDetalle;
import com.comedor.pos.domain.model.EstadoComanda;
import com.comedor.pos.domain.model.Insumo;
import com.comedor.pos.domain.model.MetodoPago;
import com.comedor.pos.domain.ports.inbound.ResultadoVenta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponse(
        Long id,
        String folioTicket,
        String folioCocina,
        Long cierreCajaId,
        Long clienteId,
        MetodoPago metodoPago,
        EstadoComanda estado,
        BigDecimal total,
        String codigoBarrasCobro,
        LocalDateTime fecha,
        List<ComandaDetalleResponse> items,
        boolean alertaStockBajo,
        String flagAlerta,
        List<InsumoAlertaResponse> insumosEnAlerta
) {

    public static VentaResponse from(ResultadoVenta resultado) {
        Comanda comanda = resultado.comanda();
        List<ComandaDetalleResponse> items = comanda.getDetalles().stream()
                .map(VentaResponse::mapDetalle)
                .toList();
        List<InsumoAlertaResponse> alertas = resultado.insumosEnAlerta().stream()
                .map(VentaResponse::mapAlerta)
                .toList();
        return new VentaResponse(
                comanda.getId(),
                comanda.getFolioTicket(),
                comanda.getFolioCocina(),
                comanda.getCierreCajaId(),
                comanda.getClienteId(),
                comanda.getMetodoPago(),
                comanda.getEstado(),
                comanda.getTotal(),
                comanda.getCodigoBarrasCobro(),
                comanda.getFecha(),
                items,
                resultado.alertaStockBajo(),
                resultado.alertaStockBajo() ? "ALERTA_STOCK_BAJO" : null,
                alertas
        );
    }

    private static ComandaDetalleResponse mapDetalle(ComandaDetalle detalle) {
        return new ComandaDetalleResponse(
                detalle.getProductoId(),
                detalle.getDescripcion(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal()
        );
    }

    private static InsumoAlertaResponse mapAlerta(Insumo insumo) {
        return new InsumoAlertaResponse(
                insumo.getId(),
                insumo.getNombre(),
                insumo.getStockActual(),
                insumo.getStockMinimo(),
                insumo.getUnidad()
        );
    }
}
