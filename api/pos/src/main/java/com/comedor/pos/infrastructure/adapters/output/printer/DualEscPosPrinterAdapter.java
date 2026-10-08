package com.comedor.pos.infrastructure.adapters.output.printer;

import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.ports.outbound.ImpresoraPort;
import com.comedor.pos.infrastructure.config.ImpresoraProperties;
import com.fazecast.jSerialComm.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DualEscPosPrinterAdapter implements ImpresoraPort {

    private static final Logger log = LoggerFactory.getLogger(DualEscPosPrinterAdapter.class);

    private final ImpresoraProperties properties;
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public DualEscPosPrinterAdapter(ImpresoraProperties properties) {
        this.properties = properties;
    }

    @Override
    public void imprimirVentaDual(Comanda comanda) {
        byte[] ticketCaja = EscPosDocumentBuilder.ticketCliente(comanda);
        byte[] comandaCocina = EscPosDocumentBuilder.comandaCocina(comanda);
        CompletableFuture<Void> caja = CompletableFuture.runAsync(
                () -> enviar(properties.getCaja(), ticketCaja, "TICKET_CLIENTE " + comanda.getFolioTicket()),
                executor
        );
        CompletableFuture<Void> cocina = CompletableFuture.runAsync(
                () -> enviar(properties.getCocina(), comandaCocina, "COMANDA_COCINA " + comanda.getFolioCocina()),
                executor
        );
        CompletableFuture.allOf(caja, cocina).join();
    }

    @Override
    public void imprimirTicketZ(CierreCaja cierreCaja) {
        byte[] ticketZ = EscPosDocumentBuilder.ticketZ(cierreCaja);
        enviar(properties.getCaja(), ticketZ, "TICKET_Z caja=" + cierreCaja.getId());
    }

    private void enviar(String puerto, byte[] payload, String etiqueta) {
        SerialPort port = SerialPort.getCommPort(puerto);
        port.setBaudRate(9600);
        port.setNumDataBits(8);
        port.setNumStopBits(SerialPort.ONE_STOP_BIT);
        port.setParity(SerialPort.NO_PARITY);
        port.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 2000, 0);
        if (!port.openPort()) {
            log.warn("Puerto {} no disponible. Simulando impresión [{}]:\n{}", puerto, etiqueta, new String(payload, StandardCharsets.ISO_8859_1));
            return;
        }
        try {
            int escritos = port.writeBytes(payload, payload.length);
            log.info("Impresión ESC/POS enviada a {} [{}], bytes={}", puerto, etiqueta, escritos);
        } finally {
            port.closePort();
        }
    }
}
