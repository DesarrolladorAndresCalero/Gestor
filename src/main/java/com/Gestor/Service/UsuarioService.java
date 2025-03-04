package com.Gestor.Service;

import com.Gestor.Model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    List<Usuario> listarUsuarios();
}
