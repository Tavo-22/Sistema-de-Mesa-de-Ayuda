package com.mesadeayuda.dto;

import java.time.LocalDateTime;

public record UsuarioResponseDto(
        Long id,
        String nombre,
        String email,
        boolean activo,
        LocalDateTime fechaCreacion
) {
}
