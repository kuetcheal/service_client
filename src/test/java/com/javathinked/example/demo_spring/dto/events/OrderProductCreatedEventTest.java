package com.javathinked.example.demo_spring.dto.events;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class OrderProductCreatedEventTest {

    @Test
    void shouldCreateOrderProductCreatedEventWithAllFields() {
        // Given
        Long id = 1L;
        Long orderId = 2L;
        Long productId = 3L;
        int quantity = 5;
        Instant createdAt = Instant.now();
        String source = "service-commande";
        int version = 1;

        // When
        OrderProductCreatedEvent event = new OrderProductCreatedEvent(id, orderId, productId, quantity, createdAt, source, version);

        // Then
        assertEquals(id, event.id());
        assertEquals(orderId, event.orderId());
        assertEquals(productId, event.productId());
        assertEquals(quantity, event.quantity());
        assertEquals(createdAt, event.createdAt());
        assertEquals(source, event.source());
        assertEquals(version, event.version());
    }

    @Test
    void shouldCreateOrderProductCreatedEventWithNullValues() {
        // Given
        Long id = null;
        Long orderId = null;
        Long productId = null;
        int quantity = 0;
        Instant createdAt = null;
        String source = null;
        int version = 0;

        // When
        OrderProductCreatedEvent event = new OrderProductCreatedEvent(id, orderId, productId, quantity, createdAt, source, version);

        // Then
        assertNull(event.id());
        assertNull(event.orderId());
        assertNull(event.productId());
        assertEquals(0, event.quantity());
        assertNull(event.createdAt());
        assertNull(event.source());
        assertEquals(0, event.version());
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        Instant now = Instant.now();
        OrderProductCreatedEvent event1 = new OrderProductCreatedEvent(1L, 2L, 3L, 5, now, "service-commande", 1);
        OrderProductCreatedEvent event2 = new OrderProductCreatedEvent(1L, 2L, 3L, 5, now, "service-commande", 1);

        // When & Then
        assertEquals(event1, event2);
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValues() {
        // Given
        Instant now = Instant.now();
        OrderProductCreatedEvent event1 = new OrderProductCreatedEvent(1L, 2L, 3L, 5, now, "service-commande", 1);
        OrderProductCreatedEvent event2 = new OrderProductCreatedEvent(2L, 2L, 3L, 5, now, "service-commande", 1);

        // When & Then
        assertNotEquals(event1, event2);
    }

    @Test
    void shouldHaveStringRepresentation() {
        // Given
        OrderProductCreatedEvent event = new OrderProductCreatedEvent(1L, 2L, 3L, 5, Instant.now(), "service-commande", 1);

        // When
        String stringRepresentation = event.toString();

        // Then
        assertNotNull(stringRepresentation);
        assertTrue(stringRepresentation.contains("OrderProductCreatedEvent"));
        assertTrue(stringRepresentation.contains("1"));
        assertTrue(stringRepresentation.contains("service-commande"));
    }
}
