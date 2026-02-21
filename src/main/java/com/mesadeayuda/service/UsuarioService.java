package com.mesadeayuda.service;

import com.mesadeayuda.dto.ActualizarUsuarioDto;
import com.mesadeayuda.dto.CrearUsuarioDto;
import com.mesadeayuda.dto.UsuarioResponseDto;
import com.mesadeayuda.model.Usuario;
import com.mesadeayuda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //crear Usuario
    public UsuarioResponseDto crearUsuario(CrearUsuarioDto crearUsuarioDto){
        Usuario existente = usuarioRepository.findByEmail(crearUsuarioDto.email())
                .orElseThrow(()->new IllegalArgumentException("Ya un usuario con el correo: "+crearUsuarioDto.email()));

        Usuario usuario = new Usuario();
        usuario.setNombre(crearUsuarioDto.nombre());
        usuario.setEmail(crearUsuarioDto.email());
        usuario.setPassword(crearUsuarioDto.password());
        usuario.setActivo(true);

        Usuario guardar = usuarioRepository.save(usuario);
        return mapToResponseUsuario(guardar);
    }

    //actualizar usuario
    public UsuarioResponseDto actualizarUsuario(Long id, ActualizarUsuarioDto actualizarUsuarioDto){
        Usuario usuario = usuarioRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));

        usuario.setNombre(actualizarUsuarioDto.nombre());
        usuario.setEmail(actualizarUsuarioDto.email());

        Usuario actualizar = usuarioRepository.save(usuario);
        return mapToResponseUsuario(actualizar);
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
    public UsuarioResponseDto buscarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Usuario no encontrado"));
        return mapToResponseUsuario(usuario);
    }

    //buscar por email
    public UsuarioResponseDto buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException("No existe el usuario con esl correo: "+email));
        return mapToResponseUsuario(usuario);
    }

    //buscar por nombre
    public List<UsuarioResponseDto> buscarPorNombre(String nombre){
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::mapToResponseUsuario)
                .toList();
    }

    //listar usuarios
    public List<UsuarioResponseDto> listarUsuarios(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponseUsuario)
                .toList();
    }

    //contar activos
    public Long contarUsuariosActivos(){
        return usuarioRepository.countByActivoTrue();
    }

    //contar inactivos
    public Long contarUsuarioInactivos(){
        return usuarioRepository.countByActivoFalse();
    }

    //mapear
    private UsuarioResponseDto mapToResponseUsuario(Usuario usuario){
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.isActivo(),
                usuario.getFechaCreacion()
        );
    }
}
