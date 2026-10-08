package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Cliente;
import com.comedor.pos.domain.ports.outbound.ClientePort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataClienteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientePersistenceAdapter implements ClientePort {

    private final SpringDataClienteRepository repository;

    public ClientePersistenceAdapter(SpringDataClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(cliente)));
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll().stream().map(PersistenceMappers::toDomain).toList();
    }
}
