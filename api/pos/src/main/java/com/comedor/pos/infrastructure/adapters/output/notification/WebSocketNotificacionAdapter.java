package com.comedor.pos.infrastructure.adapters.output.notification;

import com.comedor.pos.domain.model.Insumo;
import com.comedor.pos.domain.ports.outbound.NotificacionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSocketNotificacionAdapter implements NotificacionPort {

    private static final Logger log = LoggerFactory.getLogger(WebSocketNotificacionAdapter.class);

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketNotificacionAdapter(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void notificarReabastecimiento(List<Insumo> insumosEnAlerta) {
        if (insumosEnAlerta == null || insumosEnAlerta.isEmpty()) {
            return;
        }
        List<Map<String, Object>> payload = insumosEnAlerta.stream().map(insumo -> {
            Map<String, Object> item = new HashMap<>();
            item.put("insumoId", insumo.getId());
            item.put("nombre", insumo.getNombre());
            item.put("stockActual", insumo.getStockActual());
            item.put("stockMinimo", insumo.getStockMinimo());
            item.put("unidad", insumo.getUnidad());
            item.put("flag", "ALERTA_STOCK_BAJO");
            return item;
        }).toList();
        messagingTemplate.convertAndSend("/topic/alertas-stock", payload);
        log.warn("Aviso de reabastecimiento enviado para {} insumos", insumosEnAlerta.size());
    }
}
