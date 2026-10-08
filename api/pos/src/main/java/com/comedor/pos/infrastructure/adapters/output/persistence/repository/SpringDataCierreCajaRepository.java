package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.domain.model.EstadoCaja;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.CierreCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataCierreCajaRepository extends JpaRepository<CierreCajaEntity, Long> {

    Optional<CierreCajaEntity> findFirstByUsuarioIdAndEstado(Long usuarioId, EstadoCaja estado);
}
