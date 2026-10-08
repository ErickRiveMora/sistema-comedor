package com.comedor.pos.domain.ports.inbound;

import com.comedor.pos.domain.model.CierreCaja;

public interface CerrarCajaUseCase {

    CierreCaja ejecutar(CerrarCajaCommand command);
}
