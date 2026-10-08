package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.ComandaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SpringDataComandaRepository extends JpaRepository<ComandaEntity, Long> {

    @EntityGraph(attributePaths = "detalles")
    List<ComandaEntity> findByCierreCajaId(Long cierreCajaId);

    @Override
    @EntityGraph(attributePaths = "detalles")
    Optional<ComandaEntity> findById(Long id);

    long countByFechaGreaterThanEqualAndFechaLessThan(LocalDateTime inicio, LocalDateTime fin);
}
