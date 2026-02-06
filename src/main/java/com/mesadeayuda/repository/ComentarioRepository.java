package com.mesadeayuda.repository;

import com.mesadeayuda.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
}
