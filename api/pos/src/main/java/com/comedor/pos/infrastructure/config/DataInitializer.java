package com.comedor.pos.infrastructure.config;

import com.comedor.pos.domain.model.Categoria;
import com.comedor.pos.domain.model.Cliente;
import com.comedor.pos.domain.model.Empleado;
import com.comedor.pos.domain.model.Insumo;
import com.comedor.pos.domain.model.Producto;
import com.comedor.pos.domain.model.Receta;
import com.comedor.pos.domain.model.RolUsuario;
import com.comedor.pos.domain.model.Usuario;
import com.comedor.pos.domain.ports.outbound.CategoriaPort;
import com.comedor.pos.domain.ports.outbound.ClientePort;
import com.comedor.pos.domain.ports.outbound.EmpleadoPort;
import com.comedor.pos.domain.ports.outbound.InsumoPort;
import com.comedor.pos.domain.ports.outbound.ProductoPort;
import com.comedor.pos.domain.ports.outbound.RecetaPort;
import com.comedor.pos.domain.ports.outbound.UsuarioPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UsuarioPort usuarioPort;
    private final CategoriaPort categoriaPort;
    private final ProductoPort productoPort;
    private final InsumoPort insumoPort;
    private final RecetaPort recetaPort;
    private final ClientePort clientePort;
    private final EmpleadoPort empleadoPort;

    public DataInitializer(
            UsuarioPort usuarioPort,
            CategoriaPort categoriaPort,
            ProductoPort productoPort,
            InsumoPort insumoPort,
            RecetaPort recetaPort,
            ClientePort clientePort,
            EmpleadoPort empleadoPort
    ) {
        this.usuarioPort = usuarioPort;
        this.categoriaPort = categoriaPort;
        this.productoPort = productoPort;
        this.insumoPort = insumoPort;
        this.recetaPort = recetaPort;
        this.clientePort = clientePort;
        this.empleadoPort = empleadoPort;
    }

    @Override
    public void run(String... args) {
        if (usuarioPort.buscarPorUsername("cajero").isPresent()) {
            return;
        }

        Usuario cajero = usuarioPort.guardar(new Usuario(
                null, "cajero", "demo", "Cajero Demo", RolUsuario.CAJERO, true
        ));
        Categoria tacos = categoriaPort.guardar(new Categoria(null, "Tacos", true));
        Producto tacoPastor = productoPort.guardar(new Producto(
                null, tacos.getId(), "Taco al pastor", new BigDecimal("18.00"), "1", true
        ));
        Producto agua = productoPort.guardar(new Producto(
                null, tacos.getId(), "Agua de horchata", new BigDecimal("15.00"), "2", true
        ));
        Insumo tortilla = insumoPort.guardar(new Insumo(
                null, "Tortilla", "pza", new BigDecimal("20"), new BigDecimal("10"), new BigDecimal("0.80")
        ));
        Insumo pastor = insumoPort.guardar(new Insumo(
                null, "Carne al pastor", "kg", new BigDecimal("2.500"), new BigDecimal("1.000"), new BigDecimal("120.00")
        ));
        recetaPort.guardar(new Receta(null, tacoPastor.getId(), tortilla.getId(), new BigDecimal("1")));
        recetaPort.guardar(new Receta(null, tacoPastor.getId(), pastor.getId(), new BigDecimal("0.080")));
        Cliente cliente = clientePort.guardar(new Cliente(
                null, "Juan Pérez", "5551234567", new BigDecimal("500.00"), BigDecimal.ZERO, true
        ));
        empleadoPort.guardar(new Empleado(
                null, cajero.getNombreCompleto(), "Cajero", new BigDecimal("2500.00"), cajero.getId(), true
        ));

        log.info(
                "Datos demo cargados. usuarioId={}, productoTacoId={}, productoAguaId={}, clienteId={}",
                cajero.getId(), tacoPastor.getId(), agua.getId(), cliente.getId()
        );
    }
}
