package com.mesadeayuda.service;

import com.mesadeayuda.dto.ActualizarUsuarioDto;
import com.mesadeayuda.dto.CrearUsuarioDto;
import com.mesadeayuda.model.Usuario;
import com.mesadeayuda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //crear Usuario
    public Usuario crearUsuario(CrearUsuarioDto crearUsuarioDto){
        Optional<Usuario> existente = usuarioRepository.findByEmail(crearUsuarioDto.email());
        if(existente.isPresent()){
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(crearUsuarioDto.nombre());
        usuario.setEmail(crearUsuarioDto.email());
        usuario.setPassword(crearUsuarioDto.password());
        usuario.setActivo(true);

        return usuarioRepository.save(usuario);
    }

    //actualizar usuario
    public Usuario actualizarUsuario(Long id, ActualizarUsuarioDto actualizarUsuarioDto){
        Usuario usuario = usuarioRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));

        usuario.setNombre(actualizarUsuarioDto.nombre());
        usuario.setEmail(actualizarUsuarioDto.email());

        return usuarioRepository.save(usuario);
    }

    //eliminar usuario logico
    public void desactivarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    //eliminar usuario fisicamente
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }


    //buscar por id
    public Optional<Usuario> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    //buscar por email
    public Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    //buscar por nombre
    public List<Usuario> buscarPorNombre(String nombre){
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    //listar usuarios
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    //contar activos
    public Long contarUsuariosActivos(){
        return usuarioRepository.countByActivoTrue();
    }

    //contar inactivos
    public Long contarUsuarioInactivos(){
        return usuarioRepository.countByActivoFalse();
    }
}
