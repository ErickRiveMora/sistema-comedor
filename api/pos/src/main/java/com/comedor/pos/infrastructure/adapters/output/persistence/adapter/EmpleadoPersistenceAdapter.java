package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.Empleado;
import com.comedor.pos.domain.ports.outbound.EmpleadoPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataEmpleadoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmpleadoPersistenceAdapter implements EmpleadoPort {

    private final SpringDataEmpleadoRepository repository;

    public EmpleadoPersistenceAdapter(SpringDataEmpleadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(empleado)));
    }

    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<Empleado> listar() {
        return repository.findAll().stream().map(PersistenceMappers::toDomain).toList();
    }
}
