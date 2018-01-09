package com.example.statemachine.demo.config;

import com.example.statemachine.demo.domain.entity.TicketEvent;
import com.example.statemachine.demo.domain.entity.TicketStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
public class StateMachineConfig {

    @Configuration
    @EnableStateMachineFactory(name = "ticketStateMachineFactory")
    public static class TicketConfig extends StateMachineConfigurerAdapter<TicketStatus, TicketEvent> {

        private static final Logger logger = LoggerFactory.getLogger(TicketConfig.class);

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
                    .stateEntry(TicketStatus.CREATED, c -> {
                        logger.debug("## CREATED State  Entry!");
                    })
                    .stateDo(TicketStatus.CREATED, c -> {
                        logger.debug("## CREATED State  Do!");
                    })
                    .stateExit(TicketStatus.CREATED, c -> {
                        logger.debug("## CREATED State  Exit!");
                    })
                    .stateEntry(TicketStatus.NEW, c -> {
                        logger.debug("## NEW State  Entry!");
                    })
                    .stateDo(TicketStatus.NEW, c -> {
                        logger.debug("## NEW State  Do!");
                    })
                    .stateExit(TicketStatus.NEW, c -> {
                        logger.debug("## NEW State  Exit!");
                    })
                    .stateEntry(TicketStatus.OPEN, c -> {
                        logger.debug("## OPEN State  Entry!");
                    })
                    .stateDo(TicketStatus.OPEN, c -> {
                        logger.debug("## OPEN State  Do!");
                    })
                    .stateExit(TicketStatus.OPEN, c -> {
                        logger.debug("## OPEN State  Exit!");
                    })
                    .stateEntry(TicketStatus.PENDING, c -> {
                        logger.debug("## NEW State  Entry!");
                    })
                    .stateDo(TicketStatus.PENDING, c -> {
                        logger.debug("## NEW State  Do!");
                    })
                    .stateExit(TicketStatus.PENDING, c -> {
                        logger.debug("## PENDING State  Exit!");
                    })
                    .stateEntry(TicketStatus.ON_HOLD, c -> {
                        logger.debug("## ON_HOLD State  Entry!");
                    })
                    .stateDo(TicketStatus.ON_HOLD, c -> {
                        logger.debug("## ON_HOLD State  Do!");
                    })
                    .stateExit(TicketStatus.ON_HOLD, c -> {
                        logger.debug("## ON_HOLD State  Exit!");
                    })
                    .stateEntry(TicketStatus.SOLVED, c -> {
                        logger.debug("## SOLVED State  Entry!");
                    })
                    .stateDo(TicketStatus.SOLVED, c -> {
                        logger.debug("## SOLVED State  Do!");
                    })
                    .stateExit(TicketStatus.SOLVED, c -> {
                        logger.debug("## SOLVED State  Exit!");
                    })
                    .end(TicketStatus.CLOSED)
                    .stateEntry(TicketStatus.CLOSED, c -> {
                        logger.debug("## CLOSED State  Entry!");
                    })
                    .stateDo(TicketStatus.CLOSED, c -> {
                        logger.debug("## CLOSED State  Do!");
                    })
                    .stateExit(TicketStatus.CLOSED, c -> {
                        logger.debug("## CLOSED State  Exit!");
                    });
                    //.states(EnumSet.allOf(TicketStatus.class));
        }

        //TODO 고객등록, 관리자 등록을 구분하지 말고 구분값으로 CHOICE나 JUNCTION으로 분기처리하는 방법도 생각해보자.
        @Override
        public void configure(StateMachineTransitionConfigurer<TicketStatus, TicketEvent> transitions) throws Exception {
            transitions
                    .withExternal()
                    .source(TicketStatus.CREATED).target(TicketStatus.NEW).event(TicketEvent.NEW)       //고객이 티켓 등록
                    .action(c -> {
                        logger.debug("!! NEW Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.CREATED).target(TicketStatus.OPEN).event(TicketEvent.REGIST)   //관리자가 티켓 등록
                    .action(c -> {
                        logger.debug("!! REGIST Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.NEW).target(TicketStatus.OPEN).event(TicketEvent.ASSIGN)       //담당자 설정
                    .action(c -> {
                        logger.debug("!! ASSIGN Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.OPEN).target(TicketStatus.PENDING).event(TicketEvent.PENDING)  //고객에게 추가질문
                    .action(c -> {
                        logger.debug("!! PENDING Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.OPEN).target(TicketStatus.ON_HOLD).event(TicketEvent.HOLD)     //관리자 파트외 3자 파트에서 우선조치가 필요할 때
                    .action(c -> {
                        logger.debug("!! HOLD Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.OPEN).target(TicketStatus.SOLVED).event(TicketEvent.SOLVED)    //요청사항 해결시
                    .action(c -> {
                        logger.debug("!! SOLVED Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.PENDING).target(TicketStatus.OPEN).event(TicketEvent.ANSWER)   //추가질문에 대해 고객이 응답했을 경우
                    .action(c -> {
                        logger.debug("!! ANSWER Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.ON_HOLD).target(TicketStatus.OPEN).event(TicketEvent.REOPEN)   //3자 파트에서 우선조치 완료시
                    .action(c -> {
                        logger.debug("!! REOPEN Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.SOLVED).target(TicketStatus.OPEN).event(TicketEvent.OBJECTION) //요청사항 해결에 대해 불만이 있는 경우(고객이 응답했을 경우)
                    .action(c -> {
                        logger.debug("!! OBJECTION Event Called!");
                    })
                    .and()
                    .withExternal()
                    .source(TicketStatus.SOLVED).target(TicketStatus.CLOSED).event(TicketEvent.CLOSE)  //고객의 응답이 없고 일정시간이 지난 경우 자동 CLOSING, 배치?
                    .action(c -> {
                        logger.debug("!! CLOSE Event Called!");
                    });
        }
    }
}
