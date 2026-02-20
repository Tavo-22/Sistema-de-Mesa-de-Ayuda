package com.mesadeayuda.dto;

import com.mesadeayuda.model.PrioridadTicket;

public record ActualizarTicketDto(
        String titulo,
        String descripcion,
        PrioridadTicket prioridadTicket
) {
}
