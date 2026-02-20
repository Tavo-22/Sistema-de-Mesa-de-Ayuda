package com.mesadeayuda.dto;

import com.mesadeayuda.model.PrioridadTicket;

public record CrearTicketDto(
        String titulo,
        String descripcion,
        PrioridadTicket prioridadTicket,
        Long usuarioId
) {
}
