package com.example.statemachine.demo.domain.entity;

import lombok.Data;

@Data
public class Ticket {
    private Long id;
    private TicketStatus status;
}
