package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCompraRepository extends JpaRepository<CompraEntity, Long> {
}
