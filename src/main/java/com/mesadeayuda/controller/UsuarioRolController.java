package com.mesadeayuda.controller;

import com.mesadeayuda.dto.CrearUsuarioRolDto;
import com.mesadeayuda.dto.UsuarioRolResponseDto;
import com.mesadeayuda.service.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/usuario-roles")
public class UsuarioRolController {

    @Autowired
    private UsuarioRolService usuarioRolService;

    //asiganr rol
    @PostMapping
    public ResponseEntity<UsuarioRolResponseDto> asignarRol(@RequestBody CrearUsuarioRolDto crearUsuarioRolDto){
        UsuarioRolResponseDto rolResponseDto = usuarioRolService.asignarRol(crearUsuarioRolDto);
        return ResponseEntity.ok(rolResponseDto);
    }

    //lisatr roles por usuario
    public ResponseEntity<List<UsuarioRolResponseDto>> listarRolesDelUsuario(@PathVariable Long usuarioId){
        List<UsuarioRolResponseDto> rolResponseDtos = usuarioRolService.listarRolesDeUsuario(usuarioId);
        return ResponseEntity.ok(rolResponseDtos);
    }

    //quitar rol del usuario
    @DeleteMapping
    public ResponseEntity<Void> quitarRol(@RequestParam Long usuarioId, @RequestParam Long rolId){
        usuarioRolService.quitarRol(usuarioId, rolId);
        return ResponseEntity.noContent().build();
    }
}
