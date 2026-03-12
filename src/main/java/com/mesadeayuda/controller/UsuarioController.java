package com.mesadeayuda.controller;

import com.mesadeayuda.dto.ActualizarUsuarioDto;
import com.mesadeayuda.dto.CrearUsuarioDto;
import com.mesadeayuda.dto.UsuarioResponseDto;
import com.mesadeayuda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //crar usuario
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@RequestBody CrearUsuarioDto crearUsuarioDto){
        UsuarioResponseDto usuario = usuarioService.crearUsuario(crearUsuarioDto);
        return ResponseEntity.ok(usuario);
    }

    //lisatr usarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuario(){
        List<UsuarioResponseDto> usuario = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuario);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Long id){
        UsuarioResponseDto usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    //actualiar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> actulizarUsuario(@PathVariable Long id,
                                                               ActualizarUsuarioDto actualizarUsuarioDto){
        UsuarioResponseDto usuario = usuarioService.actualizarUsuario(id, actualizarUsuarioDto);
        return ResponseEntity.ok(usuario);
    }

    //eliminar usuario fiscio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    //buscar por email
    @GetMapping("/email")
    public ResponseEntity<UsuarioResponseDto> buscarPorEmail(@RequestParam String email){
        UsuarioResponseDto usuario = usuarioService.buscarPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    //buscar por nombre
    @GetMapping("/nombre")
    public ResponseEntity<List<UsuarioResponseDto>> buscarPorNombre(@RequestParam String nombre){
        List<UsuarioResponseDto> usuario = usuarioService.buscarPorNombre(nombre);
        return ResponseEntity.ok(usuario);
    }

    //desactivar usuario logico
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Long id){
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    //contar activos
    @GetMapping("/activos/count")
    public ResponseEntity<Long> contarUsuariosActivos(){
        Long total = usuarioService.contarUsuariosActivos();
        return ResponseEntity.ok(total);
    }

    //contar inactivos
    @GetMapping("/inactivos/count")
    public ResponseEntity<Long> contarUsuariosInactivos(){
        Long total = usuarioService.contarUsuarioInactivos();
        return ResponseEntity.ok(total);
    }

}
