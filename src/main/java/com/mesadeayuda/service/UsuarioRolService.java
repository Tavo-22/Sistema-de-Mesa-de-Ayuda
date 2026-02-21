package com.mesadeayuda.service;

import com.mesadeayuda.dto.CrearUsuarioRolDto;
import com.mesadeayuda.dto.UsuarioRolResponseDto;
import com.mesadeayuda.model.Rol;
import com.mesadeayuda.model.Usuario;
import com.mesadeayuda.model.UsuarioRol;
import com.mesadeayuda.repository.RolRepository;
import com.mesadeayuda.repository.UsuarioRepository;
import com.mesadeayuda.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    //asignar rol
    public UsuarioRolResponseDto asignarRol(CrearUsuarioRolDto crearUsuarioRolDto){
        Usuario usuario = usuarioRepository.findById(crearUsuarioRolDto.usuarioId())
                .orElseThrow(()->new IllegalArgumentException("Usuario no encontrador con el id: "+crearUsuarioRolDto.usuarioId()));

        Rol rol = rolRepository.findById(crearUsuarioRolDto.rolId())
                .orElseThrow(()->new IllegalArgumentException("Rol no encontrado con el id: "+crearUsuarioRolDto.rolId()));

        if(usuarioRolRepository.existsByUsuarioIdAndRolId(
                crearUsuarioRolDto.usuarioId(), crearUsuarioRolDto.rolId()
        )){
            throw new RuntimeException("El usaurio ya tiene este rol asignado");
        }
        UsuarioRol usuarioRol = new UsuarioRol(usuario,rol);
        UsuarioRol guardar = usuarioRolRepository.save(usuarioRol);

        return mapToResponse(guardar);
    }

    //quitar rol
    public void quitarRol(Long usuarioId, Long rolId){
        UsuarioRol usuarioRol = usuarioRolRepository
                .findByUsuarioIdAndRolId(usuarioId, rolId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        usuarioRolRepository.delete(usuarioRol);
    }

    //listar rolres de un usuario
    public List<UsuarioRolResponseDto> listarRolesDeUsuario(Long usuarioId) {

        return usuarioRolRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    private UsuarioRolResponseDto mapToResponse(UsuarioRol usuarioRol){
        return new UsuarioRolResponseDto(
                usuarioRol.getId(),
                usuarioRol.getUsuario().getId(),
                usuarioRol.getUsuario().getNombre(),
                usuarioRol.getRol().getId(),
                usuarioRol.getRol().getNombre(),
                usuarioRol.getFechaAsignacion()
        );
    }
}
