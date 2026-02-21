package com.mesadeayuda.service;

import com.mesadeayuda.dto.ComentarioResponseDto;
import com.mesadeayuda.dto.CrearComentarioDto;
import com.mesadeayuda.model.Comentario;
import com.mesadeayuda.model.Ticket;
import com.mesadeayuda.model.Usuario;
import com.mesadeayuda.repository.ComentarioRepository;
import com.mesadeayuda.repository.TicketRepository;
import com.mesadeayuda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //crear comentario
    public ComentarioResponseDto crearComentario(CrearComentarioDto crearComentarioDto){
        Ticket ticket = ticketRepository.findById(crearComentarioDto.ticketId())
                .orElseThrow(()->new IllegalArgumentException("Ticket no encontrado"));

        Usuario usuario = usuarioRepository.findById(crearComentarioDto.autorId())
                .orElseThrow(()->new IllegalArgumentException("Usuario no encontrado"));

        Comentario comentario = new Comentario(
                crearComentarioDto.contenido(),
                ticket,
                usuario
        );

        Comentario guardar = comentarioRepository.save(comentario);
        return mapToResponse(guardar);
    }

    //listar comentario
    public List<ComentarioResponseDto> listarPorTickets(Long ticket){
        return comentarioRepository.findByTicketId(ticket)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //buscar comentario id
    public ComentarioResponseDto buscarPorId(Long id){
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Comentario no encontrado"));

        return mapToResponse(comentario);
    }

    //eliminar comentario
    public void eliminarComentario(Long id){
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Comentario no encontrado"));

        comentarioRepository.deleteById(id);
    }

    private ComentarioResponseDto mapToResponse(Comentario comentario) {
        return new ComentarioResponseDto(
                comentario.getId(),
                comentario.getContenido(),
                comentario.getTicket().getId(),
                comentario.getTicket().getTitulo(),
                comentario.getAutor().getId(),
                comentario.getAutor().getNombre(),
                comentario.getFechaCreacion()
        );
    }
}
