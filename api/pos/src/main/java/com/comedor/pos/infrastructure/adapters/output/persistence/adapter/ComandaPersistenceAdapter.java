package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.ports.outbound.ComandaPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataComandaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ComandaPersistenceAdapter implements ComandaPort {

    private final SpringDataComandaRepository repository;

    public ComandaPersistenceAdapter(SpringDataComandaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comanda guardar(Comanda comanda) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(comanda)));
    }

    @Override
    public Optional<Comanda> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Comanda> listarPorCierreCaja(Long cierreCajaId) {
        return repository.findByCierreCajaId(cierreCajaId).stream().map(PersistenceMappers::toDomain).toList();
    }

    @Override
    public long contarFoliosDelDia(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.plusDays(1).atStartOfDay();
        return repository.countByFechaGreaterThanEqualAndFechaLessThan(inicio, fin);
    }
}
