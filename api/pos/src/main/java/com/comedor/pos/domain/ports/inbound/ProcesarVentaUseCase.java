package com.comedor.pos.domain.ports.inbound;

public interface ProcesarVentaUseCase {

    ResultadoVenta ejecutar(ProcesarVentaCommand command);
}
