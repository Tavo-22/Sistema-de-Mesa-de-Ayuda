package com.mesadeayuda.dto;

import java.time.LocalDateTime;

public record ComentarioResponseDto(
        Long id,
        String contenido,
        Long ticketId,
        String tituloTicket,
        Long autorId,
        String nombreAutor,
        LocalDateTime fechaCreacion
) {
}
