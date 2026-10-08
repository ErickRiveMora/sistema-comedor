package com.comedor.pos.domain.service;

import com.comedor.pos.domain.exception.RecursoNoEncontradoException;
import com.comedor.pos.domain.model.ComandaDetalle;
import com.comedor.pos.domain.model.Insumo;
import com.comedor.pos.domain.model.Receta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventarioDomainService {

    public ResultadoDescuentoStock descontarPorReceta(
            List<ComandaDetalle> detalles,
            List<Receta> recetas,
            List<Insumo> insumos
    ) {
        Map<Long, List<Receta>> recetasPorProducto = recetas.stream()
                .collect(Collectors.groupingBy(Receta::getProductoId));
        Map<Long, Insumo> insumosPorId = insumos.stream()
                .collect(Collectors.toMap(Insumo::getId, insumo -> insumo, (a, b) -> a, LinkedHashMap::new));

        Map<Long, BigDecimal> consumoPorInsumo = new LinkedHashMap<>();
        for (ComandaDetalle detalle : detalles) {
            List<Receta> recetaProducto = recetasPorProducto.getOrDefault(detalle.getProductoId(), List.of());
            for (Receta receta : recetaProducto) {
                consumoPorInsumo.merge(
                        receta.getInsumoId(),
                        receta.consumoPara(detalle.getCantidad()),
                        BigDecimal::add
                );
            }
        }

        List<Insumo> actualizados = new ArrayList<>();
        List<Insumo> alertas = new ArrayList<>();
        for (Map.Entry<Long, BigDecimal> consumo : consumoPorInsumo.entrySet()) {
            Insumo insumo = insumosPorId.get(consumo.getKey());
            if (insumo == null) {
                throw new RecursoNoEncontradoException("Insumo", consumo.getKey());
            }
            insumo.descontar(consumo.getValue());
            actualizados.add(insumo);
            if (insumo.estaEnAlertaStockBajo()) {
                alertas.add(insumo);
            }
        }
        return new ResultadoDescuentoStock(actualizados, alertas);
    }

    public record ResultadoDescuentoStock(List<Insumo> insumosActualizados, List<Insumo> insumosEnAlerta) {
    }
}
