package com.comedor.pos.infrastructure.adapters.output.persistence.repository;

import com.comedor.pos.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
