package com.mesadeayuda.repository;

import com.mesadeayuda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
    //Optional<Usuario> findByNombre(String nombre);
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    long countByActivoTrue();
    long countByActivoFalse();
}
