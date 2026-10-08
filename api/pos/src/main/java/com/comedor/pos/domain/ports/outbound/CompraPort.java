package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraPort {

    Compra guardar(Compra compra);

    Optional<Compra> buscarPorId(Long id);

    List<Compra> listar();
}
