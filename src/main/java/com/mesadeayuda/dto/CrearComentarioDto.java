package com.mesadeayuda.dto;

public record CrearComentarioDto(
        String contenido,
        Long ticketId,
        Long autorId
) {
}
