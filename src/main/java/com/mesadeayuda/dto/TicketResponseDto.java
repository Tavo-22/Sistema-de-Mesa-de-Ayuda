package com.mesadeayuda.dto;

import com.mesadeayuda.model.EstadoTicket;
import com.mesadeayuda.model.PrioridadTicket;

import java.time.LocalDateTime;

public record TicketResponseDto(
        Long id,
        String titulo,
        String descripcion,
        EstadoTicket estado,
        PrioridadTicket prioridad,
        String nombreUsuario,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaCierre
) {
}
