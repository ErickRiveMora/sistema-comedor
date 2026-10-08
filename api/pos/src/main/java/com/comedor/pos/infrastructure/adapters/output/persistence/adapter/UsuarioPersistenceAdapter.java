package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Usuario;
import com.comedor.pos.domain.ports.outbound.UsuarioPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioPersistenceAdapter implements UsuarioPort {

    private final SpringDataUsuarioRepository repository;

    public UsuarioPersistenceAdapter(SpringDataUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(usuario)));
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return repository.findByUsername(username).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Usuario> listar() {
        return repository.findAll().stream().map(PersistenceMappers::toDomain).toList();
    }
}
