package com.javathinked.example.demo_spring.dto.events;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ProductStockUpdatedEventTest {

    @Test
    void shouldCreateProductStockUpdatedEventWithAllFields() {
        // Given
        Long productId = 1L;
        int newStock = 10;
        Instant updatedAt = Instant.now();
        String source = "service-produit";
        int version = 1;

        // When
        ProductStockUpdatedEvent event = new ProductStockUpdatedEvent(productId, newStock, updatedAt, source, version);

        // Then
        assertEquals(productId, event.productId());
        assertEquals(newStock, event.newStock());
        assertEquals(updatedAt, event.updatedAt());
        assertEquals(source, event.source());
        assertEquals(version, event.version());
    }

    @Test
    void shouldCreateProductStockUpdatedEventWithNullValues() {
        // Given
        Long productId = null;
        int newStock = 0;
        Instant updatedAt = null;
        String source = null;
        int version = 0;

        // When
        ProductStockUpdatedEvent event = new ProductStockUpdatedEvent(productId, newStock, updatedAt, source, version);

        // Then
        assertNull(event.productId());
        assertEquals(0, event.newStock());
        assertNull(event.updatedAt());
        assertNull(event.source());
        assertEquals(0, event.version());
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        Instant now = Instant.now();
        ProductStockUpdatedEvent event1 = new ProductStockUpdatedEvent(1L, 10, now, "service-produit", 1);
        ProductStockUpdatedEvent event2 = new ProductStockUpdatedEvent(1L, 10, now, "service-produit", 1);

        // When & Then
        assertEquals(event1, event2);
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValues() {
        // Given
        Instant now = Instant.now();
        ProductStockUpdatedEvent event1 = new ProductStockUpdatedEvent(1L, 10, now, "service-produit", 1);
        ProductStockUpdatedEvent event2 = new ProductStockUpdatedEvent(2L, 10, now, "service-produit", 1);

        // When & Then
        assertNotEquals(event1, event2);
    }

    @Test
    void shouldHaveStringRepresentation() {
        // Given
        ProductStockUpdatedEvent event = new ProductStockUpdatedEvent(1L, 10, Instant.now(), "service-produit", 1);

        // When
        String stringRepresentation = event.toString();

        // Then
        assertNotNull(stringRepresentation);
        assertTrue(stringRepresentation.contains("ProductStockUpdatedEvent"));
        assertTrue(stringRepresentation.contains("1"));
        assertTrue(stringRepresentation.contains("service-produit"));
    }
}
