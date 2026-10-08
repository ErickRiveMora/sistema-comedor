package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.RecetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SpringDataRecetaRepository extends JpaRepository<RecetaEntity, Long> {

    List<RecetaEntity> findByProductoIdIn(Collection<Long> productoIds);
}
