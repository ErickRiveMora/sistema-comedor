package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProductoRepository extends JpaRepository<ProductoEntity, Long> {

    List<ProductoEntity> findByActivoTrue();
}
