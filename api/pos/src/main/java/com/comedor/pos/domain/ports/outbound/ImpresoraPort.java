package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Comanda;

public interface ImpresoraPort {

    void imprimirVentaDual(Comanda comanda);

    void imprimirTicketZ(CierreCaja cierreCaja);
}
