package com.mesadeayuda.service;

import com.mesadeayuda.dto.ActualizarTicketDto;
import com.mesadeayuda.dto.CrearTicketDto;
import com.mesadeayuda.dto.TicketResponseDto;
import com.mesadeayuda.model.EstadoTicket;
import com.mesadeayuda.model.Ticket;
import com.mesadeayuda.model.Usuario;
import com.mesadeayuda.repository.TicketRepository;
import com.mesadeayuda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    // - crear ticket
    public TicketResponseDto crearTicket(CrearTicketDto crearTicketDto){
        Usuario usuario = usuarioRepository.findById(crearTicketDto.usuarioId())
                .orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));

        Ticket ticket = new Ticket();
        ticket.setTitulo(crearTicketDto.titulo());
        ticket.setDescripcion(crearTicketDto.descripcion());
        ticket.setPrioridad(crearTicketDto.prioridadTicket());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setUsuarioCreador(usuario);
        ticket.setFechaCreacion(LocalDateTime.now());

        Ticket guardado = ticketRepository.save(ticket);

        return mapToResponse(guardado);
    }
    // - listar tickets
    public List<TicketResponseDto> listarTickets(){
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    // - buscar por id
    public TicketResponseDto buscarPorId(Long id){
        Ticket ticket=ticketRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Ticket no encontrado"));

        return mapToResponse(ticket);
    }
    // - actualizar ticket
    public TicketResponseDto actualizarTicket(Long id, ActualizarTicketDto actualizarTicketDto){

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Ticket no encontrado"));

        ticket.setTitulo(actualizarTicketDto.titulo());
        ticket.setDescripcion(actualizarTicketDto.descripcion());
        ticket.setPrioridad(actualizarTicketDto.prioridadTicket());

        Ticket actualizado = ticketRepository.save(ticket);

        return mapToResponse(actualizado);
    }
    // - cambiar estado
    public TicketResponseDto cambiarEstado(Long id, EstadoTicket estadoTicket){
        Ticket ticket
                 = ticketRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Ticket no encontrado"));

        ticket.setEstado(estadoTicket);

        Ticket cambiarEstado = ticketRepository.save(ticket);
        return mapToResponse(cambiarEstado);
    }
    // - cerrar ticket
    public TicketResponseDto cerrarTicket(Long id){

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Ticket no encontrado"));

        ticket.setEstado(EstadoTicket.CERRADO);
        ticket.setFechaCierre(LocalDateTime.now());

        Ticket cerrar = ticketRepository.save(ticket);
        return mapToResponse(cerrar);
    }
    // - buscar por titulo
    public List<TicketResponseDto> buscarPorTitulo(String titulo){
        return ticketRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    // - buscar por usuarios
    public List<TicketResponseDto> buscarPorNombreUsuario(String nombre) {
        return ticketRepository
                .findByCreadorNombreContainingIgnoreCaseOrAsignadoNombreContainingIgnoreCase(
                        nombre,
                        nombre
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    // - listar por estado
    public List<TicketResponseDto> listarPorEstado(EstadoTicket estado) {
        return ticketRepository.findByEstado(estado)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private TicketResponseDto mapToResponse(Ticket ticket) {
        return new TicketResponseDto(
                ticket.getId(),
                ticket.getTitulo(),
                ticket.getDescripcion(),
                ticket.getEstado(),
                ticket.getPrioridad(),
                ticket.getUsuarioCreador().getNombre(), // importante
                ticket.getFechaCreacion(),
                ticket.getFechaCierre()
        );
    }


}
