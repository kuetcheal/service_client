package com.javathinked.example.demo_spring.dto.events;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderCreatedEventTest {

    @Test
    void shouldCreateOrderCreatedEventWithAllFields() {
        // Given
        Long orderId = 1L;
        Long customerId = 2L;
        List<Long> productIds = List.of(3L, 4L);
        Instant createdAt = Instant.now();
        String source = "service-commande";
        int version = 1;

        // When
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, customerId, productIds, createdAt, source, version);

        // Then
        assertEquals(orderId, event.orderId());
        assertEquals(customerId, event.customerId());
        assertEquals(productIds, event.productIds());
        assertEquals(createdAt, event.createdAt());
        assertEquals(source, event.source());
        assertEquals(version, event.version());
    }

    @Test
    void shouldCreateOrderCreatedEventWithNullValues() {
        // Given
        Long orderId = null;
        Long customerId = null;
        List<Long> productIds = null;
        Instant createdAt = null;
        String source = null;
        int version = 0;

        // When
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, customerId, productIds, createdAt, source, version);

        // Then
        assertNull(event.orderId());
        assertNull(event.customerId());
        assertNull(event.productIds());
        assertNull(event.createdAt());
        assertNull(event.source());
        assertEquals(0, event.version());
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        Instant now = Instant.now();
        OrderCreatedEvent event1 = new OrderCreatedEvent(1L, 2L, List.of(3L, 4L), now, "service-commande", 1);
        OrderCreatedEvent event2 = new OrderCreatedEvent(1L, 2L, List.of(3L, 4L), now, "service-commande", 1);

        // When & Then
        assertEquals(event1, event2);
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValues() {
        // Given
        Instant now = Instant.now();
        OrderCreatedEvent event1 = new OrderCreatedEvent(1L, 2L, List.of(3L, 4L), now, "service-commande", 1);
        OrderCreatedEvent event2 = new OrderCreatedEvent(2L, 2L, List.of(3L, 4L), now, "service-commande", 1);

        // When & Then
        assertNotEquals(event1, event2);
    }

    @Test
    void shouldHaveStringRepresentation() {
        // Given
        OrderCreatedEvent event = new OrderCreatedEvent(1L, 2L, List.of(3L, 4L), Instant.now(), "service-commande", 1);

        // When
        String stringRepresentation = event.toString();

        // Then
        assertNotNull(stringRepresentation);
        assertTrue(stringRepresentation.contains("OrderCreatedEvent"));
        assertTrue(stringRepresentation.contains("1"));
        assertTrue(stringRepresentation.contains("service-commande"));
    }
}
