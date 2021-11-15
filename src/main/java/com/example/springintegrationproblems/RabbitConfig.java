package com.example.springintegrationproblems;

import java.util.Collections;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private static Map<String, Object> defaultQueueArgs() {
        return Collections.singletonMap("x-queue-mode", "lazy");
    }

    @Bean
    public Queue inbound() {
        return new Queue("inbound", true, false, false, defaultQueueArgs());
    }
    
    @Bean
    public Queue out() {
        return new Queue("out", true, false, false, defaultQueueArgs());
    }
    
    @Bean
    public Queue outAgain() {
        return new Queue("outAgain", true, false, false, defaultQueueArgs());
    }
}
