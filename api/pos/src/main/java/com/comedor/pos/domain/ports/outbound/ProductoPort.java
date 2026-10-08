package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Producto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductoPort {

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(Long id);

    List<Producto> buscarPorIds(Collection<Long> ids);

    List<Producto> listarActivos();
}
