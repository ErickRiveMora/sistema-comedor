package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Insumo;

import java.util.List;

public interface NotificacionPort {

    void notificarReabastecimiento(List<Insumo> insumosEnAlerta);
}
