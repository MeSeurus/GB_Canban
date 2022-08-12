package com.canban.web.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {
    public static final String ADD_TO_EVENT_GET_EMAILS = "add-to-event-get-emails";
    public static final String REMOVE_FROM_EVENT = "status-change";
    public static final String MAKE_TASK_EXECUTOR = "activation";
    public static final String ADD_TO_BOARD = "password-remind";

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
