package com.example.statemachine.demo.config;

import com.example.statemachine.demo.domain.entity.TicketEvent;
import com.example.statemachine.demo.domain.entity.TicketStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

import java.util.LinkedList;
import java.util.List;

public class StateMachineEventListener extends StateMachineListenerAdapter<TicketStatus, TicketEvent> {

    private static final Logger logger = LoggerFactory.getLogger(StateMachineEventListener.class);

    private final LinkedList<String> messages = new LinkedList<String>();

    public List<String> getMessages() {
        return messages;
    }

    public void resetMessages() {
        messages.clear();
    }

    @Override
    public void stateContext(StateContext<TicketStatus, TicketEvent> stateContext) {

        if (stateContext.getStage() == StateContext.Stage.STATE_ENTRY) {
            //logger.debug("%%% {}", stateContext.getStage());
            if(stateContext.getEvent() == TicketEvent.ASSIGN) {
                logger.debug("%%%1 {}", stateContext.getEvent());
                logger.debug("%%%2 {}", stateContext.getTargets());
                logger.debug("%%%3 {}", stateContext.getSources());
            }

        }
        /*if (stateContext.getStage() == StateContext.Stage.STATE_ENTRY) {
            messages.addFirst(stateContext.getStateMachine().getId() + " enter " + stateContext.getTarget().getId());
        } else if (stateContext.getStage() == StateContext.Stage.STATE_EXIT) {
            messages.addFirst(stateContext.getStateMachine().getId() + " exit " + stateContext.getSource().getId());
        }*/
    }
}
