package com.mesadeayuda.dto;

import java.time.LocalDateTime;

public record UsuarioRolResponseDto(
        Long id,
        Long usuarioId,
        String nombreUsuario,
        Long rolId,
        String nombreRol,
        LocalDateTime fechaAsignacion
) {
}
