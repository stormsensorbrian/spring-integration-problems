package com.example.springintegrationproblems;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import com.example.springintegrationproblems.service.UppercaseService;

@Configuration
public class BasicInOutFlow {

    @Autowired
    AmqpTemplate template;
    
    @Autowired
    ConnectionFactory factory;
    
    @Autowired
    UppercaseService service;
    
    @Bean
    public IntegrationFlow basicInOut() {
        return IntegrationFlows.from(Amqp.inboundAdapter(factory, "inbound").id("inboundEndpoint"))
                .handle(service, "makeItBig")
                .handle(Amqp.outboundAdapter(template).routingKey("out"), e -> e.id("outboundEndpoint"))
                .get();
    }
    
    @Bean
    public IntegrationFlow secondFlow() {
        return IntegrationFlows.from(Amqp.inboundAdapter(factory, "out").id("secondInboundEndpoint"))
                .handle(Amqp.outboundAdapter(template).routingKey("outAgain"), e -> e.id("outboundAgainEndpoint"))
                .get();
    }
    
}
