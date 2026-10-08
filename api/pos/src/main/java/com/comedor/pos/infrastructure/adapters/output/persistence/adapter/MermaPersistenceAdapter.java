package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Merma;
import com.comedor.pos.domain.ports.outbound.MermaPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataMermaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MermaPersistenceAdapter implements MermaPort {

    private final SpringDataMermaRepository repository;

    public MermaPersistenceAdapter(SpringDataMermaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Merma guardar(Merma merma) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(merma)));
    }

    @Override
    public Optional<Merma> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Merma> listar() {
        return repository.findAll().stream().map(PersistenceMappers::toDomain).toList();
    }
}
