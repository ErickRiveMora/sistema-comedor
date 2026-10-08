package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Categoria;
import com.comedor.pos.domain.ports.outbound.CategoriaPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataCategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriaPersistenceAdapter implements CategoriaPort {

    private final SpringDataCategoriaRepository repository;

    public CategoriaPersistenceAdapter(SpringDataCategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(categoria)));
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Categoria> listar() {
        return repository.findAll().stream().map(PersistenceMappers::toDomain).toList();
    }
}
