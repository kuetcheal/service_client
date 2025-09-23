package com.javathinked.example.demo_spring.messaging;

import com.javathinked.example.demo_spring.dto.events.ClientCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    private ClientPublisher clientPublisher;

    @BeforeEach
    void setUp() {
        clientPublisher = new ClientPublisher(rabbitTemplate);
    }

    @Test
    void shouldPublishClientCreatedEvent() {
        // Given
        ClientCreatedEvent event = new ClientCreatedEvent(
            1L,
            Instant.now(),
            "service-client",
            1
        );

        // When
        clientPublisher.publishClientCreated(event);

        // Then
        ArgumentCaptor<ClientCreatedEvent> eventCaptor = ArgumentCaptor.forClass(ClientCreatedEvent.class);
        verify(rabbitTemplate).convertAndSend(
            eq("app.exchange"),
            eq("client.created"),
            eventCaptor.capture()
        );
        
        ClientCreatedEvent capturedEvent = eventCaptor.getValue();
        assertEquals(event.clientId(), capturedEvent.clientId());
        assertEquals(event.source(), capturedEvent.source());
        assertEquals(event.version(), capturedEvent.version());
    }

    @Test
    void shouldPublishClientCreatedEventWithNullValues() {
        // Given
        ClientCreatedEvent event = new ClientCreatedEvent(
            null,
            null,
            null,
            0
        );

        // When
        clientPublisher.publishClientCreated(event);

        // Then
        verify(rabbitTemplate).convertAndSend(
            eq("app.exchange"),
            eq("client.created"),
            eq(event)
        );
    }

    @Test
    void shouldPublishClientCreatedEventMultipleTimes() {
        // Given
        ClientCreatedEvent event1 = new ClientCreatedEvent(1L, Instant.now(), "service-client", 1);
        ClientCreatedEvent event2 = new ClientCreatedEvent(2L, Instant.now(), "service-client", 1);

        // When
        clientPublisher.publishClientCreated(event1);
        clientPublisher.publishClientCreated(event2);

        // Then
        verify(rabbitTemplate, times(2)).convertAndSend(
            eq("app.exchange"),
            eq("client.created"),
            any(ClientCreatedEvent.class)
        );
    }
}
