package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Receta;
import com.comedor.pos.domain.ports.outbound.RecetaPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataRecetaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class RecetaPersistenceAdapter implements RecetaPort {

    private final SpringDataRecetaRepository repository;

    public RecetaPersistenceAdapter(SpringDataRecetaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Receta guardar(Receta receta) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(receta)));
    }

    @Override
    public List<Receta> buscarPorProductoIds(Collection<Long> productoIds) {
        if (productoIds == null || productoIds.isEmpty()) {
            return List.of();
        }
        return repository.findByProductoIdIn(productoIds).stream().map(PersistenceMappers::toDomain).toList();
    }
}
