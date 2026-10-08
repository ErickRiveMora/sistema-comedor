package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.NominaMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataNominaMovimientoRepository extends JpaRepository<NominaMovimientoEntity, Long> {

    List<NominaMovimientoEntity> findByEmpleadoId(Long empleadoId);
}
