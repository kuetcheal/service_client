package com.javathinked.example.demo_spring.dto.events;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ClientCreatedEventTest {

    @Test
    void shouldCreateClientCreatedEventWithAllFields() {
        // Given
        Long clientId = 1L;
        Instant createdAt = Instant.now();
        String source = "service-client";
        int version = 1;

        // When
        ClientCreatedEvent event = new ClientCreatedEvent(clientId, createdAt, source, version);

        // Then
        assertEquals(clientId, event.clientId());
        assertEquals(createdAt, event.createdAt());
        assertEquals(source, event.source());
        assertEquals(version, event.version());
    }

    @Test
    void shouldCreateClientCreatedEventWithNullValues() {
        // Given
        Long clientId = null;
        Instant createdAt = null;
        String source = null;
        int version = 0;

        // When
        ClientCreatedEvent event = new ClientCreatedEvent(clientId, createdAt, source, version);

        // Then
        assertNull(event.clientId());
        assertNull(event.createdAt());
        assertNull(event.source());
        assertEquals(0, event.version());
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        Instant now = Instant.now();
        ClientCreatedEvent event1 = new ClientCreatedEvent(1L, now, "service-client", 1);
        ClientCreatedEvent event2 = new ClientCreatedEvent(1L, now, "service-client", 1);

        // When & Then
        assertEquals(event1, event2);
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValues() {
        // Given
        Instant now = Instant.now();
        ClientCreatedEvent event1 = new ClientCreatedEvent(1L, now, "service-client", 1);
        ClientCreatedEvent event2 = new ClientCreatedEvent(2L, now, "service-client", 1);

        // When & Then
        assertNotEquals(event1, event2);
    }

    @Test
    void shouldHaveStringRepresentation() {
        // Given
        ClientCreatedEvent event = new ClientCreatedEvent(1L, Instant.now(), "service-client", 1);

        // When
        String stringRepresentation = event.toString();

        // Then
        assertNotNull(stringRepresentation);
        assertTrue(stringRepresentation.contains("ClientCreatedEvent"));
        assertTrue(stringRepresentation.contains("1"));
        assertTrue(stringRepresentation.contains("service-client"));
    }
}
