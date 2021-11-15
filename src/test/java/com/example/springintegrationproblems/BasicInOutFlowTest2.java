package com.example.springintegrationproblems;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.endpoint.AbstractEndpoint;
import org.springframework.integration.test.context.MockIntegrationContext;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.integration.test.mock.MockIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SpringIntegrationTest(noAutoStartup = {"secondInboundEndpoint"})
class BasicInOutFlowTest2 {
    
    @Autowired
    MockIntegrationContext context;
    
    @Autowired
    AmqpTemplate template;

    @Autowired
    @Qualifier("secondInboundEndpoint")
    AbstractEndpoint inbound;
    
    @Test
    void testFlow() {
        assertFalse(inbound.isRunning());
        
        MessageHandler messageHandler = MockIntegration.mockMessageHandler().handleNext(m ->{});
        context.substituteMessageHandlerFor("outboundAgainEndpoint", messageHandler);
        ArgumentCaptor<Message<?>> captor = MockIntegration.messageArgumentCaptor();
        
        inbound.start();
        
        Awaitility.await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            assertTrue(inbound.isRunning());
        });
        
        template.convertAndSend("out", "foo");
        
        verify(messageHandler, timeout(5000)).handleMessage(captor.capture());
        Message<String> response = (Message<String>) captor.getValue();
        assertThat(response.getPayload()).isEqualTo("foo");
    }
}
