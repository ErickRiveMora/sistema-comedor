package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Comanda;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ComandaPort {

    Comanda guardar(Comanda comanda);

    Optional<Comanda> buscarPorId(Long id);

    List<Comanda> listarPorCierreCaja(Long cierreCajaId);

    long contarFoliosDelDia(LocalDate fecha);
}
