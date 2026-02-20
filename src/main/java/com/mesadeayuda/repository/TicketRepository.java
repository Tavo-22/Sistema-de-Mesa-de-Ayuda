package com.mesadeayuda.repository;

import com.mesadeayuda.model.EstadoTicket;
import com.mesadeayuda.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTituloContainingIgnoreCase(String titulo);
    List<Ticket> findByCreadorNombreContainingIgnoreCaseOrAsignadoNombreContainingIgnoreCase(
            String creador,
            String asignado
    );
    List<Ticket> findByEstado(EstadoTicket estado);
}
