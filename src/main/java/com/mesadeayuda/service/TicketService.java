package com.mesadeayuda.service;

import com.mesadeayuda.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Mañana aquí iremos agregando:
    // - crear ticket
    // - listar tickets
    // - buscar por id
    // - actualizar ticket
    // - cambiar estado
    // - cerrar ticket
}
