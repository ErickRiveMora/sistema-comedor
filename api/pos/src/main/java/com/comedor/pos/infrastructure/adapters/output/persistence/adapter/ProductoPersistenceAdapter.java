package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Producto;
import com.comedor.pos.domain.ports.outbound.ProductoPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataProductoRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class ProductoPersistenceAdapter implements ProductoPort {

    private final SpringDataProductoRepository repository;

    public ProductoPersistenceAdapter(SpringDataProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Producto guardar(Producto producto) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(producto)));
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Producto> buscarPorIds(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return repository.findAllById(ids).stream().map(PersistenceMappers::toDomain).toList();
    }

    @Override
    public List<Producto> listarActivos() {
        return repository.findByActivoTrue().stream().map(PersistenceMappers::toDomain).toList();
    }
}
