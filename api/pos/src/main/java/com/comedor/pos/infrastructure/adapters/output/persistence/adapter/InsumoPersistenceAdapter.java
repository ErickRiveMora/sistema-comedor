package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Insumo;
import com.comedor.pos.domain.ports.outbound.InsumoPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataInsumoRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class InsumoPersistenceAdapter implements InsumoPort {

    private final SpringDataInsumoRepository repository;

    public InsumoPersistenceAdapter(SpringDataInsumoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Insumo guardar(Insumo insumo) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(insumo)));
    }

    @Override
    public List<Insumo> guardarTodos(Collection<Insumo> insumos) {
        return repository.saveAll(insumos.stream().map(PersistenceMappers::toEntity).toList())
                .stream()
                .map(PersistenceMappers::toDomain)
                .toList();
    }

    @Override
    public Optional<Insumo> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Insumo> buscarPorIds(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return repository.findAllById(ids).stream().map(PersistenceMappers::toDomain).toList();
    }

    @Override
    public List<Insumo> listar() {
        return repository.findAll().stream().map(PersistenceMappers::toDomain).toList();
    }
}
