package com.mesadeayuda.controller;

import com.mesadeayuda.dto.ComentarioResponseDto;
import com.mesadeayuda.dto.CrearComentarioDto;
import com.mesadeayuda.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    //crear comentario
    @PostMapping
    public ResponseEntity<ComentarioResponseDto> crearComentario(@RequestBody CrearComentarioDto crearComentarioDto){
        ComentarioResponseDto comentarioResponseDto = comentarioService.crearComentario(crearComentarioDto);
        return ResponseEntity.ok(comentarioResponseDto);
    }

    //listar comentario por ticket
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<ComentarioResponseDto>> listarPorTicket(@PathVariable Long id){
        List<ComentarioResponseDto> comentarios = comentarioService.listarPorTickets(id);
        return ResponseEntity.ok(comentarios);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDto> buscarPorId(@PathVariable Long id){
        ComentarioResponseDto comentarioResponseDto = comentarioService.buscarPorId(id);
        return ResponseEntity.ok(comentarioResponseDto);
    }

    //eliminar comentario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id){
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

}
