package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
