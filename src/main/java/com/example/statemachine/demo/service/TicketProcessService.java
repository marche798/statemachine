package com.example.statemachine.demo.service;

import com.example.statemachine.demo.domain.entity.Ticket;
import com.example.statemachine.demo.domain.entity.TicketEvent;
import com.example.statemachine.demo.domain.entity.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

@Service
public class TicketProcessService {
    @Autowired
    @Qualifier("ticketStateMachineFactory")
    StateMachineFactory<TicketStatus, TicketEvent> stateMachineFactory;

    public StateMachine<TicketStatus, TicketEvent> getStateMachine(Ticket ticket) {
        StateMachine<TicketStatus, TicketEvent> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.getStateMachineAccessor().doWithAllRegions(access -> {
            access.resetStateMachine(
                    new DefaultStateMachineContext<TicketStatus, TicketEvent>(
                            ticket.getStatus(), null, null, null, null, null));
        });
        return stateMachine;
    }
}
