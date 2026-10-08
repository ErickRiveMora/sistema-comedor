package com.comedor.pos.domain.ports.inbound;

import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.model.Insumo;

import java.util.List;

public record ResultadoVenta(
        Comanda comanda,
        boolean alertaStockBajo,
        List<Insumo> insumosEnAlerta
) {
}
