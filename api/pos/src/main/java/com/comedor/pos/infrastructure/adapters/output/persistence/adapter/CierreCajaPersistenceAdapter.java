package com.comedor.pos.infrastructure.adapters.output.persistence.adapter;

import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.EstadoCaja;
import com.comedor.pos.domain.ports.outbound.CierreCajaPort;
import com.comedor.pos.infrastructure.adapters.output.persistence.mapper.PersistenceMappers;
import com.comedor.pos.infrastructure.adapters.output.persistence.repository.SpringDataCierreCajaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CierreCajaPersistenceAdapter implements CierreCajaPort {

    private final SpringDataCierreCajaRepository repository;

    public CierreCajaPersistenceAdapter(SpringDataCierreCajaRepository repository) {
        this.repository = repository;
    }

    @Override
    public CierreCaja guardar(CierreCaja cierreCaja) {
        return PersistenceMappers.toDomain(repository.save(PersistenceMappers.toEntity(cierreCaja)));
    }

    @Override
    public Optional<CierreCaja> buscarPorId(Long id) {
        return repository.findById(id).map(PersistenceMappers::toDomain);
    }

    @Override
    public Optional<CierreCaja> buscarAbiertaPorUsuario(Long usuarioId) {
        return repository.findFirstByUsuarioIdAndEstado(usuarioId, EstadoCaja.ABIERTA)
                .map(PersistenceMappers::toDomain);
    }
}
