package com.comedor.pos.domain.service;

import com.comedor.pos.domain.exception.CajaCerradaException;
import com.comedor.pos.domain.exception.CajaYaAbiertaException;
import com.comedor.pos.domain.exception.ValidacionNegocioException;
import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CajaDomainService {

    public CierreCaja abrir(Usuario usuario, BigDecimal fondoInicial, Optional<CierreCaja> cajaAbierta, LocalDateTime ahora) {
        if (usuario == null || !usuario.isActivo()) {
            throw new ValidacionNegocioException("El usuario no existe o no está activo para abrir caja.");
        }
        if (cajaAbierta.isPresent()) {
            throw new CajaYaAbiertaException(usuario.getId());
        }
        return CierreCaja.abrir(usuario.getId(), fondoInicial, ahora);
    }

    public CierreCaja cerrar(CierreCaja caja, List<Comanda> ventasTurno, BigDecimal efectivoDeclarado, LocalDateTime ahora) {
        if (caja == null) {
            throw new CajaCerradaException();
        }
        caja.cerrarConArqueo(ventasTurno, efectivoDeclarado, ahora);
        return caja;
    }
}
