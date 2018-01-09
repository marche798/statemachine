package com.example.statemachine.demo.config;

import com.example.statemachine.demo.domain.entity.TicketEvent;
import com.example.statemachine.demo.domain.entity.TicketStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
public class StateMachineConfig {

    @Configuration
    @EnableStateMachineFactory(name = "ticketStateMachineFactory")
    public static class TicketConfig extends StateMachineConfigurerAdapter<TicketStatus, TicketEvent> {

        @Override
        public void configure(StateMachineConfigurationConfigurer<TicketStatus, TicketEvent> config)
                throws Exception {
            config
                    .withConfiguration()
                    .autoStartup(true);
        }

        @Override
        public void configure(StateMachineStateConfigurer<TicketStatus, TicketEvent> states) throws Exception {
            states
                    .withStates()
                    .initial(TicketStatus.CREATED)
                    //.end(TicketStatus.)
                    .states(EnumSet.allOf(TicketStatus.class));
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<TicketStatus, TicketEvent> transitions) throws Exception {
            transitions
                    .withExternal()
                    .source(TicketStatus.CREATED).target(TicketStatus.NEW).event(TicketEvent.NEW)
                    .and()
                    .withExternal()
                    .source(TicketStatus.NEW).target(TicketStatus.OPEN).event(TicketEvent.ASSIGN)
                    .and()
                    .withExternal()
                    .source(TicketStatus.OPEN).target(TicketStatus.PENDING).event(TicketEvent.PENDING)
                    .and()
                    .withExternal()
                    .source(TicketStatus.PENDING).target(TicketStatus.SOLVED).event(TicketEvent.SOLVED)
                    .and()
                    .withExternal()
                    .source(TicketStatus.OPEN).target(TicketStatus.SOLVED).event(TicketEvent.SOLVED);
        }
    }
}
