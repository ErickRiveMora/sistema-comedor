package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClientePort {

    Cliente guardar(Cliente cliente);

    Optional<Cliente> buscarPorId(Long id);

    List<Cliente> listar();
}
