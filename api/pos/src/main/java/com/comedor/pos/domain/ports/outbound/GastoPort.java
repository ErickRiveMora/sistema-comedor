package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Gasto;

import java.util.List;
import java.util.Optional;

public interface GastoPort {

    Gasto guardar(Gasto gasto);

    Optional<Gasto> buscarPorId(Long id);

    List<Gasto> listar();
}
