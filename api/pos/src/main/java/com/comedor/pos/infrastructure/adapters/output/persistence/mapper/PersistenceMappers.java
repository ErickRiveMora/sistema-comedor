package com.comedor.pos.infrastructure.adapters.output.persistence.mapper;

import com.comedor.pos.domain.model.Categoria;
import com.comedor.pos.domain.model.CierreCaja;
import com.comedor.pos.domain.model.Cliente;
import com.comedor.pos.domain.model.Comanda;
import com.comedor.pos.domain.model.ComandaDetalle;
import com.comedor.pos.domain.model.Compra;
import com.comedor.pos.domain.model.Empleado;
import com.comedor.pos.domain.model.Gasto;
import com.comedor.pos.domain.model.Insumo;
import com.comedor.pos.domain.model.Merma;
import com.comedor.pos.domain.model.NominaMovimiento;
import com.comedor.pos.domain.model.Producto;
import com.comedor.pos.domain.model.Receta;
import com.comedor.pos.domain.model.Usuario;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.CategoriaEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.CierreCajaEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.ComandaDetalleEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.ComandaEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.CompraEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.EmpleadoEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.GastoEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.InsumoEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.MermaEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.NominaMovimientoEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.RecetaEntity;
import com.comedor.pos.infrastructure.adapters.output.persistence.entity.UsuarioEntity;

import java.util.List;

public final class PersistenceMappers {

    private PersistenceMappers() {
    }

    public static Categoria toDomain(CategoriaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Categoria(entity.getId(), entity.getNombre(), entity.isActiva());
    }

    public static CategoriaEntity toEntity(Categoria domain) {
        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setActiva(domain.isActiva());
        return entity;
    }

    public static Producto toDomain(ProductoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Producto(
                entity.getId(),
                entity.getCategoriaId(),
                entity.getNombre(),
                entity.getPrecioVenta(),
                entity.getCodigoAtajo(),
                entity.isActivo()
        );
    }

    public static ProductoEntity toEntity(Producto domain) {
        ProductoEntity entity = new ProductoEntity();
        entity.setId(domain.getId());
        entity.setCategoriaId(domain.getCategoriaId());
        entity.setNombre(domain.getNombre());
        entity.setPrecioVenta(domain.getPrecioVenta());
        entity.setCodigoAtajo(domain.getCodigoAtajo());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public static Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Cliente(
                entity.getId(),
                entity.getNombre(),
                entity.getTelefono(),
                entity.getLimiteCredito(),
                entity.getSaldoPendiente(),
                entity.isActivo()
        );
    }

    public static ClienteEntity toEntity(Cliente domain) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setTelefono(domain.getTelefono());
        entity.setLimiteCredito(domain.getLimiteCredito());
        entity.setSaldoPendiente(domain.getSaldoPendiente());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public static Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Usuario(
                entity.getId(),
                entity.getUsername(),
                entity.getPasswordHash(),
                entity.getNombreCompleto(),
                entity.getRol(),
                entity.isActivo()
        );
    }

    public static UsuarioEntity toEntity(Usuario domain) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setPasswordHash(domain.getPasswordHash());
        entity.setNombreCompleto(domain.getNombreCompleto());
        entity.setRol(domain.getRol());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public static Insumo toDomain(InsumoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Insumo(
                entity.getId(),
                entity.getNombre(),
                entity.getUnidad(),
                entity.getStockActual(),
                entity.getStockMinimo(),
                entity.getCostoUnitario()
        );
    }

    public static InsumoEntity toEntity(Insumo domain) {
        InsumoEntity entity = new InsumoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setUnidad(domain.getUnidad());
        entity.setStockActual(domain.getStockActual());
        entity.setStockMinimo(domain.getStockMinimo());
        entity.setCostoUnitario(domain.getCostoUnitario());
        return entity;
    }

    public static Receta toDomain(RecetaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Receta(entity.getId(), entity.getProductoId(), entity.getInsumoId(), entity.getCantidadPorPorcion());
    }

    public static RecetaEntity toEntity(Receta domain) {
        RecetaEntity entity = new RecetaEntity();
        entity.setId(domain.getId());
        entity.setProductoId(domain.getProductoId());
        entity.setInsumoId(domain.getInsumoId());
        entity.setCantidadPorPorcion(domain.getCantidadPorPorcion());
        return entity;
    }

    public static CierreCaja toDomain(CierreCajaEntity entity) {
        if (entity == null) {
            return null;
        }
        CierreCaja domain = new CierreCaja();
        domain.setId(entity.getId());
        domain.setUsuarioId(entity.getUsuarioId());
        domain.setFondoInicial(entity.getFondoInicial());
        domain.setEstado(entity.getEstado());
        domain.setFechaApertura(entity.getFechaApertura());
        domain.setFechaCierre(entity.getFechaCierre());
        domain.setEfectivoDeclarado(entity.getEfectivoDeclarado());
        domain.setTotalEfectivoEsperado(entity.getTotalEfectivoEsperado());
        domain.setDiferenciaArqueo(entity.getDiferenciaArqueo());
        domain.setTotalEfectivo(entity.getTotalEfectivo());
        domain.setTotalTarjeta(entity.getTotalTarjeta());
        domain.setTotalTransferencia(entity.getTotalTransferencia());
        domain.setTotalFiado(entity.getTotalFiado());
        domain.setGananciaNeta(entity.getGananciaNeta());
        return domain;
    }

    public static CierreCajaEntity toEntity(CierreCaja domain) {
        CierreCajaEntity entity = new CierreCajaEntity();
        entity.setId(domain.getId());
        entity.setUsuarioId(domain.getUsuarioId());
        entity.setFondoInicial(domain.getFondoInicial());
        entity.setEstado(domain.getEstado());
        entity.setFechaApertura(domain.getFechaApertura());
        entity.setFechaCierre(domain.getFechaCierre());
        entity.setEfectivoDeclarado(domain.getEfectivoDeclarado());
        entity.setTotalEfectivoEsperado(domain.getTotalEfectivoEsperado());
        entity.setDiferenciaArqueo(domain.getDiferenciaArqueo());
        entity.setTotalEfectivo(domain.getTotalEfectivo());
        entity.setTotalTarjeta(domain.getTotalTarjeta());
        entity.setTotalTransferencia(domain.getTotalTransferencia());
        entity.setTotalFiado(domain.getTotalFiado());
        entity.setGananciaNeta(domain.getGananciaNeta());
        return entity;
    }

    public static Comanda toDomain(ComandaEntity entity) {
        if (entity == null) {
            return null;
        }
        Comanda domain = new Comanda();
        domain.setId(entity.getId());
        domain.setFolioTicket(entity.getFolioTicket());
        domain.setFolioCocina(entity.getFolioCocina());
        domain.setCierreCajaId(entity.getCierreCajaId());
        domain.setUsuarioId(entity.getUsuarioId());
        domain.setClienteId(entity.getClienteId());
        domain.setMetodoPago(entity.getMetodoPago());
        domain.setTotal(entity.getTotal());
        domain.setCodigoBarrasCobro(entity.getCodigoBarrasCobro());
        domain.setFecha(entity.getFecha());
        domain.setEstado(entity.getEstado());
        domain.setDetalles(entity.getDetalles().stream().map(PersistenceMappers::toDomain).toList());
        return domain;
    }

    public static ComandaDetalle toDomain(ComandaDetalleEntity entity) {
        ComandaDetalle domain = new ComandaDetalle();
        domain.setId(entity.getId());
        domain.setComandaId(entity.getComanda() != null ? entity.getComanda().getId() : null);
        domain.setProductoId(entity.getProductoId());
        domain.setDescripcion(entity.getDescripcion());
        domain.setCantidad(entity.getCantidad());
        domain.setPrecioUnitario(entity.getPrecioUnitario());
        domain.setSubtotal(entity.getSubtotal());
        return domain;
    }

    public static ComandaEntity toEntity(Comanda domain) {
        ComandaEntity entity = new ComandaEntity();
        entity.setId(domain.getId());
        entity.setFolioTicket(domain.getFolioTicket());
        entity.setFolioCocina(domain.getFolioCocina());
        entity.setCierreCajaId(domain.getCierreCajaId());
        entity.setUsuarioId(domain.getUsuarioId());
        entity.setClienteId(domain.getClienteId());
        entity.setMetodoPago(domain.getMetodoPago());
        entity.setTotal(domain.getTotal());
        entity.setCodigoBarrasCobro(domain.getCodigoBarrasCobro());
        entity.setFecha(domain.getFecha());
        entity.setEstado(domain.getEstado());
        List<ComandaDetalleEntity> detalles = domain.getDetalles().stream().map(PersistenceMappers::toEntity).toList();
        entity.reemplazarDetalles(detalles);
        return entity;
    }

    public static ComandaDetalleEntity toEntity(ComandaDetalle domain) {
        ComandaDetalleEntity entity = new ComandaDetalleEntity();
        entity.setId(domain.getId());
        entity.setProductoId(domain.getProductoId());
        entity.setDescripcion(domain.getDescripcion());
        entity.setCantidad(domain.getCantidad());
        entity.setPrecioUnitario(domain.getPrecioUnitario());
        entity.setSubtotal(domain.getSubtotal());
        return entity;
    }

    public static Compra toDomain(CompraEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Compra(
                entity.getId(),
                entity.getInsumoId(),
                entity.getCantidad(),
                entity.getCostoTotal(),
                entity.getProveedor(),
                entity.getUsuarioId(),
                entity.getFecha()
        );
    }

    public static CompraEntity toEntity(Compra domain) {
        CompraEntity entity = new CompraEntity();
        entity.setId(domain.getId());
        entity.setInsumoId(domain.getInsumoId());
        entity.setCantidad(domain.getCantidad());
        entity.setCostoTotal(domain.getCostoTotal());
        entity.setProveedor(domain.getProveedor());
        entity.setUsuarioId(domain.getUsuarioId());
        entity.setFecha(domain.getFecha());
        return entity;
    }

    public static Gasto toDomain(GastoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Gasto(
                entity.getId(),
                entity.getConcepto(),
                entity.getMonto(),
                entity.getCategoriaGasto(),
                entity.getUsuarioId(),
                entity.getFecha()
        );
    }

    public static GastoEntity toEntity(Gasto domain) {
        GastoEntity entity = new GastoEntity();
        entity.setId(domain.getId());
        entity.setConcepto(domain.getConcepto());
        entity.setMonto(domain.getMonto());
        entity.setCategoriaGasto(domain.getCategoriaGasto());
        entity.setUsuarioId(domain.getUsuarioId());
        entity.setFecha(domain.getFecha());
        return entity;
    }

    public static Empleado toDomain(EmpleadoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Empleado(
                entity.getId(),
                entity.getNombre(),
                entity.getPuesto(),
                entity.getSueldoBase(),
                entity.getUsuarioId(),
                entity.isActivo()
        );
    }

    public static EmpleadoEntity toEntity(Empleado domain) {
        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setPuesto(domain.getPuesto());
        entity.setSueldoBase(domain.getSueldoBase());
        entity.setUsuarioId(domain.getUsuarioId());
        entity.setActivo(domain.isActivo());
        return entity;
    }

    public static NominaMovimiento toDomain(NominaMovimientoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new NominaMovimiento(
                entity.getId(),
                entity.getEmpleadoId(),
                entity.getTipo(),
                entity.getMonto(),
                entity.getFecha(),
                entity.getReferenciaComandaId(),
                entity.getConcepto()
        );
    }

    public static NominaMovimientoEntity toEntity(NominaMovimiento domain) {
        NominaMovimientoEntity entity = new NominaMovimientoEntity();
        entity.setId(domain.getId());
        entity.setEmpleadoId(domain.getEmpleadoId());
        entity.setTipo(domain.getTipo());
        entity.setMonto(domain.getMonto());
        entity.setFecha(domain.getFecha());
        entity.setReferenciaComandaId(domain.getReferenciaComandaId());
        entity.setConcepto(domain.getConcepto());
        return entity;
    }

    public static Merma toDomain(MermaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Merma(
                entity.getId(),
                entity.getInsumoId(),
                entity.getCantidad(),
                entity.getMotivo(),
                entity.getUsuarioId(),
                entity.getFecha()
        );
    }

    public static MermaEntity toEntity(Merma domain) {
        MermaEntity entity = new MermaEntity();
        entity.setId(domain.getId());
        entity.setInsumoId(domain.getInsumoId());
        entity.setCantidad(domain.getCantidad());
        entity.setMotivo(domain.getMotivo());
        entity.setUsuarioId(domain.getUsuarioId());
        entity.setFecha(domain.getFecha());
        return entity;
    }
}
