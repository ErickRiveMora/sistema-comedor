package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.InsumoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataInsumoRepository extends JpaRepository<InsumoEntity, Long> {
}
