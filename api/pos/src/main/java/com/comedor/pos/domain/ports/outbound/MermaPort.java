package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Merma;

import java.util.List;
import java.util.Optional;

public interface MermaPort {

    Merma guardar(Merma merma);

    Optional<Merma> buscarPorId(Long id);

    List<Merma> listar();
}
