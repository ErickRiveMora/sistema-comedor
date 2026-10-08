package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.GastoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataGastoRepository extends JpaRepository<GastoEntity, Long> {
}
