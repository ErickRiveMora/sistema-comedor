package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Gasto;
import com.comedor.pos.domain.ports.outbound.GastoPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataGastoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GastoPersistenceAdapter implements GastoPort {

    private final SpringDataGastoRepository repository;

    public GastoPersistenceAdapter(SpringDataGastoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Gasto guardar(Gasto gasto) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(gasto)));
    }

    @Override
    public Optional<Gasto> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Gasto> listar() {
        return repository.findAll().stream().map(PersistenceMappers::toDomain).toList();
    }
}
