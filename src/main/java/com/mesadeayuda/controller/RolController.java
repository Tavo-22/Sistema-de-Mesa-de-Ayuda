package com.mesadeayuda.controller;

import com.mesadeayuda.dto.CrearRolDto;
import com.mesadeayuda.dto.RolResponseDto;
import com.mesadeayuda.service.RolService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    //crar rol
    @PostMapping
    public ResponseEntity<RolResponseDto> crearRol(@RequestBody CrearRolDto crearRolDto){
        RolResponseDto rolResponseDto = rolService.crearRol(crearRolDto);
        return ResponseEntity.ok(rolResponseDto);
    }

    //listar rol
    @GetMapping
    public ResponseEntity<List<RolResponseDto>> listarRoles(){
        List<RolResponseDto> rolResponseDtos = rolService.listarRoles();
        return ResponseEntity.ok(rolResponseDtos);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDto> buscarPorId(@PathVariable Long id){
        RolResponseDto rolResponseDto = rolService.buscarPorId(id);
        return ResponseEntity.ok(rolResponseDto);
    }

    //buscar por nombre
    @GetMapping("/nombre")
    public ResponseEntity<RolResponseDto> buscarPorNombre(@RequestParam String nombre){
        RolResponseDto rolResponseDto = rolService.buscarPorNombre(nombre);
        return ResponseEntity.ok(rolResponseDto);
    }
}
