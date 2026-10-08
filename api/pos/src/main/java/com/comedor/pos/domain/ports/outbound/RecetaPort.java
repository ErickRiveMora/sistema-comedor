package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Receta;

import java.util.Collection;
import java.util.List;

public interface RecetaPort {

    Receta guardar(Receta receta);

    List<Receta> buscarPorProductoIds(Collection<Long> productoIds);
}
