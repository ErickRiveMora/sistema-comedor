package com.comedor.pos.domain.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FolioDomainService {

    private static final DateTimeFormatter DIA = DateTimeFormatter.BASIC_ISO_DATE;

    public String folioTicket(LocalDate fecha, long secuencia) {
        return "TICK-" + fecha.format(DIA) + "-" + pad(secuencia);
    }

    public String folioCocina(LocalDate fecha, long secuencia) {
        return "COM-" + fecha.format(DIA) + "-" + pad(secuencia);
    }

    public String codigoBarrasFiado(String folioTicket) {
        return "FIADO-" + folioTicket;
    }

    private String pad(long secuencia) {
        return String.format("%04d", secuencia);
    }
}
