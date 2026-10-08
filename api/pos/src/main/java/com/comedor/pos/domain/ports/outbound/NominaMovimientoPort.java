package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.NominaMovimiento;

import java.util.List;
import java.util.Optional;

public interface NominaMovimientoPort {

    NominaMovimiento guardar(NominaMovimiento movimiento);

    Optional<NominaMovimiento> buscarPorId(Long id);

    List<NominaMovimiento> listarPorEmpleado(Long empleadoId);
}
