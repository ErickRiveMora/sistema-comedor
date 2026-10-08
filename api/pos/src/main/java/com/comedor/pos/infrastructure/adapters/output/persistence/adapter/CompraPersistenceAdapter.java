package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Compra;
import com.comedor.pos.domain.ports.outbound.CompraPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataCompraRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompraPersistenceAdapter implements CompraPort {

    private final SpringDataCompraRepository repository;

    public CompraPersistenceAdapter(SpringDataCompraRepository repository) {
        this.repository = repository;
    }

    @Override
    public Compra guardar(Compra compra) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(compra)));
    }

    @Override
    public Optional<Compra> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Compra> listar() {
        return repository.findAll().stream().map(PersistenceMappers::toDomain).toList();
    }
}
