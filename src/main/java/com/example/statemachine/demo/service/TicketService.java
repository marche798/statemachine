package com.example.statemachine.demo.service;

import com.example.statemachine.demo.domain.entity.Ticket;
import com.example.statemachine.demo.domain.entity.TicketStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    public Ticket getTicket(Long ticketId, TicketStatus status) {
        Ticket ticket =  new Ticket();
        ticket.setId(ticketId);
        ticket.setStatus(status);
        return ticket;
    }
}
