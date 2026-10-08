package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoPort {

    Empleado guardar(Empleado empleado);

    Optional<Empleado> buscarPorId(Long id);

    List<Empleado> listar();
}
