package com.comedor.pos.domain.ports.outbound;

import com.comedor.pos.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPort {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorUsername(String username);

    List<Usuario> listar();
}
