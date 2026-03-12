package com.mesadeayuda.controller;

import com.mesadeayuda.dto.ActualizarTicketDto;
import com.mesadeayuda.dto.CrearTicketDto;
import com.mesadeayuda.dto.TicketResponseDto;
import com.mesadeayuda.model.EstadoTicket;
import com.mesadeayuda.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    //crear ticketc
    @PostMapping
    public ResponseEntity<TicketResponseDto> crearTicket(@RequestBody CrearTicketDto crearTicketDto){
        TicketResponseDto ticketResponseDto = ticketService.crearTicket(crearTicketDto);
        return ResponseEntity.ok(ticketResponseDto);
    }

    //listar tickets
    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> listarTickets(){
        List<TicketResponseDto> ticketResponseDtos = ticketService.listarTickets();
        return ResponseEntity.ok(ticketResponseDtos);
    }

    //buscar tickete por id
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> buscarPorId(@PathVariable Long id){
        TicketResponseDto ticketResponseDto = ticketService.buscarPorId(id);
        return ResponseEntity.ok(ticketResponseDto);
    }

    //actualizar ticket
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDto> actulizarTicket(@PathVariable Long id,
                                                             @RequestBody ActualizarTicketDto actualizarTicketDto){
        TicketResponseDto ticketResponseDto = ticketService.actualizarTicket(id, actualizarTicketDto);
        return ResponseEntity.ok(ticketResponseDto);
    }

    //cambiar estado del ticket
    @PatchMapping("/{id}/estado")
    public ResponseEntity<TicketResponseDto> cambiarEstadoTicket(@PathVariable Long id,
                                                                 @RequestParam EstadoTicket estadoTicket){
        TicketResponseDto ticketResponseDto = ticketService.cambiarEstado(id, estadoTicket);
        return ResponseEntity.ok(ticketResponseDto);
    }

    //cerrar ticket
    @PutMapping("/{id}/cerrar")
    public ResponseEntity<TicketResponseDto> cerrarTicket(@PathVariable Long id){
        TicketResponseDto ticketResponseDto = ticketService.cerrarTicket(id);
        return ResponseEntity.ok(ticketResponseDto);
    }

    //buscar por titulo
    @GetMapping("/titulo")
    public ResponseEntity<List<TicketResponseDto>> buscarPorTitulo(@RequestParam String titulo){
        List<TicketResponseDto> ticketResponseDtos = ticketService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(ticketResponseDtos);
    }

    //buscar por usuario
    @GetMapping("/usuario")
    public ResponseEntity<List<TicketResponseDto>> buscarPorUsuario(@RequestParam String nombre){
        List<TicketResponseDto> ticketResponseDtos = ticketService.buscarPorNombreUsuario(nombre);
        return ResponseEntity.ok(ticketResponseDtos);
    }
}
