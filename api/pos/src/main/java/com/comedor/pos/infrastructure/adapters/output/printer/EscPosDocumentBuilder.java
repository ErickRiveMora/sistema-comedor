package com.comedor.pos.infrastructure.adapters.output.printer;

import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.model.ComandaDetalle;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class EscPosDocumentBuilder {

    private static final Charset LATIN = Charset.forName("ISO-8859-1");
    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private EscPosDocumentBuilder() {
    }

    public static byte[] ticketCliente(Comanda comanda) {
        StringBuilder sb = new StringBuilder();
        sb.append("*** COMEDOR ***\n");
        sb.append("TICKET DE VENTA\n");
        sb.append("Folio: ").append(comanda.getFolioTicket()).append('\n');
        sb.append(FECHA.format(comanda.getFecha())).append('\n');
        sb.append("----------------------------\n");
        for (ComandaDetalle detalle : comanda.getDetalles()) {
            sb.append(detalle.getCantidad())
                    .append(" x ")
                    .append(detalle.getDescripcion())
                    .append('\n');
            sb.append("   $").append(detalle.getSubtotal()).append('\n');
        }
        sb.append("----------------------------\n");
        sb.append("TOTAL: $").append(comanda.getTotal()).append('\n');
        sb.append("PAGO: ").append(comanda.getMetodoPago()).append('\n');
        if (comanda.getCodigoBarrasCobro() != null) {
            sb.append("CB: ").append(comanda.getCodigoBarrasCobro()).append('\n');
        }
        sb.append("Gracias por su compra\n");
        return toEscPos(sb.toString());
    }

    public static byte[] comandaCocina(Comanda comanda) {
        StringBuilder sb = new StringBuilder();
        sb.append("*** COCINA ***\n");
        sb.append("Folio: ").append(comanda.getFolioCocina()).append('\n');
        sb.append(FECHA.format(comanda.getFecha())).append('\n');
        sb.append("----------------------------\n");
        for (ComandaDetalle detalle : comanda.getDetalles()) {
            sb.append(detalle.getCantidad())
                    .append(" x ")
                    .append(detalle.getDescripcion())
                    .append('\n');
        }
        sb.append("----------------------------\n");
        return toEscPos(sb.toString());
    }

    public static byte[] ticketZ(CierreCaja cierre) {
        StringBuilder sb = new StringBuilder();
        sb.append("*** CIERRE Z ***\n");
        sb.append("Caja: ").append(cierre.getId()).append('\n');
        sb.append("Usuario: ").append(cierre.getUsuarioId()).append('\n');
        sb.append("Apertura: ").append(FECHA.format(cierre.getFechaApertura())).append('\n');
        if (cierre.getFechaCierre() != null) {
            sb.append("Cierre: ").append(FECHA.format(cierre.getFechaCierre())).append('\n');
        }
        sb.append("----------------------------\n");
        sb.append("Fondo inicial: $").append(cierre.getFondoInicial()).append('\n');
        sb.append("Efectivo: $").append(cierre.getTotalEfectivo()).append('\n');
        sb.append("Tarjeta: $").append(cierre.getTotalTarjeta()).append('\n');
        sb.append("Transferencia: $").append(cierre.getTotalTransferencia()).append('\n');
        sb.append("Fiado: $").append(cierre.getTotalFiado()).append('\n');
        sb.append("----------------------------\n");
        sb.append("Efectivo esperado: $").append(cierre.getTotalEfectivoEsperado()).append('\n');
        sb.append("Efectivo declarado: $").append(cierre.getEfectivoDeclarado()).append('\n');
        sb.append("Diferencia arqueo: $").append(cierre.getDiferenciaArqueo()).append('\n');
        sb.append("Ganancia neta: $").append(cierre.getGananciaNeta()).append('\n');
        sb.append("----------------------------\n");
        return toEscPos(sb.toString());
    }

    private static byte[] toEscPos(String texto) {
        List<Byte> buffer = new ArrayList<>();
        add(buffer, new byte[]{0x1B, 0x40});
        add(buffer, new byte[]{0x1B, 0x61, 0x01});
        add(buffer, texto.getBytes(LATIN));
        add(buffer, "\n\n\n".getBytes(StandardCharsets.US_ASCII));
        add(buffer, new byte[]{0x1D, 0x56, 0x00});
        byte[] data = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            data[i] = buffer.get(i);
        }
        return data;
    }

    private static void add(List<Byte> buffer, byte[] chunk) {
        for (byte b : chunk) {
            buffer.add(b);
        }
    }
}
