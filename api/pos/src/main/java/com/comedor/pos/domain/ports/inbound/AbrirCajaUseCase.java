package com.comedor.pos.domain.ports.inbound;

import com.comedor.pos.domain.model.CierreCaja;

public interface AbrirCajaUseCase {

    CierreCaja ejecutar(AbrirCajaCommand command);
}
