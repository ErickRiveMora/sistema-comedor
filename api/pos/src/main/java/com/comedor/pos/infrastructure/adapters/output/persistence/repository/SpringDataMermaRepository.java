package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.MermaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMermaRepository extends JpaRepository<MermaEntity, Long> {
}
