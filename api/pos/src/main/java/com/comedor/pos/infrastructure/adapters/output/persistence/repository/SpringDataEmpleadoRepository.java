package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
}
