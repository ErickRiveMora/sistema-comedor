package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Insumo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InsumoPort {

    Insumo guardar(Insumo insumo);

    List<Insumo> guardarTodos(Collection<Insumo> insumos);

    Optional<Insumo> buscarPorId(Long id);

    List<Insumo> buscarPorIds(Collection<Long> ids);

    List<Insumo> listar();
}
