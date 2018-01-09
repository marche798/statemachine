package com.example.statemachine.demo.service;

import com.example.statemachine.demo.DemoApplication;
import com.example.statemachine.demo.domain.entity.TicketEvent;
import com.example.statemachine.demo.domain.entity.TicketStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = {DemoApplication.class})
//@ContextConfiguration(classes = {StateMachineConfig.class}, loader=AnnotationConfigContextLoader.class)
public class TicketStateMachineTest {

    private static  final Logger logger = LoggerFactory.getLogger(TicketStateMachineTest.class);

    @Autowired
    @Qualifier("ticketStateMachineFactory")
    StateMachineFactory<TicketStatus, TicketEvent> stateMachineFactory;

    @Test
    @Transactional
    public void test1() {
        StateMachine<TicketStatus, TicketEvent> stateMachine = stateMachineFactory.getStateMachine();
        //logger.debug("@@ {}",stateMachine.getTransitions().iterator().next());
        logger.debug("----------------------------------");
        stateMachine.sendEvent(TicketEvent.NEW);
        logger.debug("----------------------------------");
        stateMachine.sendEvent(TicketEvent.ASSIGN);
        logger.debug("----------------------------------");
    }
}
