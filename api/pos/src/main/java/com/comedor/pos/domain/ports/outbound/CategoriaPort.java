package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaPort {

    Categoria guardar(Categoria categoria);

    Optional<Categoria> buscarPorId(Long id);

    List<Categoria> listar();
}
