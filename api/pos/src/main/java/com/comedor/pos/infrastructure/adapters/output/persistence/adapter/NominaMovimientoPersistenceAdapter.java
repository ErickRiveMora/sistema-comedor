package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.NominaMovimiento;
import com.comedor.pos.domain.ports.outbound.NominaMovimientoPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataNominaMovimientoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NominaMovimientoPersistenceAdapter implements NominaMovimientoPort {

    private final SpringDataNominaMovimientoRepository repository;

    public NominaMovimientoPersistenceAdapter(SpringDataNominaMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public NominaMovimiento guardar(NominaMovimiento movimiento) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(movimiento)));
    }

    @Override
    public Optional<NominaMovimiento> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public List<NominaMovimiento> listarPorEmpleado(Long empleadoId) {
        return repository.findByEmpleadoId(empleadoId).stream().map(PersistenceMappers::toDomain).toList();
    }
}
