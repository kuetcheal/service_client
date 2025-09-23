package com.javathinked.example.demo_spring.messaging;

import com.javathinked.example.demo_spring.dto.events.OrderCreatedEvent;
import com.javathinked.example.demo_spring.dto.events.OrderProductCreatedEvent;
import com.javathinked.example.demo_spring.dto.events.ProductStockUpdatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientConsumersTest {

    @InjectMocks
    private ClientConsumers clientConsumers;

    @BeforeEach
    void setUp() {
        // Pas de mocks nécessaires car les méthodes sont simples
    }

    @Test
    void shouldHandleOrderCreatedEvent() {
        // Given
        OrderCreatedEvent event = new OrderCreatedEvent(
            1L,
            2L,
            List.of(3L, 4L),
            Instant.now(),
            "service-commande",
            1
        );

        // When & Then - ne doit pas lever d'exception
        assertDoesNotThrow(() -> clientConsumers.handleOrderCreated(event));
    }

    @Test
    void shouldHandleOrderCreatedEventWithNullValues() {
        // Given
        OrderCreatedEvent event = new OrderCreatedEvent(
            null,
            null,
            null,
            null,
            null,
            0
        );

        // When & Then - ne doit pas lever d'exception
        assertDoesNotThrow(() -> clientConsumers.handleOrderCreated(event));
    }

    @Test
    void shouldHandleProductStockUpdatedEvent() {
        // Given
        ProductStockUpdatedEvent event = new ProductStockUpdatedEvent(
            1L,
            10,
            Instant.now(),
            "service-produit",
            1
        );

        // When & Then - ne doit pas lever d'exception
        assertDoesNotThrow(() -> clientConsumers.handleProductStockUpdated(event));
    }

    @Test
    void shouldHandleProductStockUpdatedEventWithNullValues() {
        // Given
        ProductStockUpdatedEvent event = new ProductStockUpdatedEvent(
            null,
            0,
            null,
            null,
            0
        );

        // When & Then - ne doit pas lever d'exception
        assertDoesNotThrow(() -> clientConsumers.handleProductStockUpdated(event));
    }

    @Test
    void shouldHandleOrderProductCreatedEvent() {
        // Given
        OrderProductCreatedEvent event = new OrderProductCreatedEvent(
            1L,
            2L,
            3L,
            5,
            Instant.now(),
            "service-commande",
            1
        );

        // When & Then - ne doit pas lever d'exception
        assertDoesNotThrow(() -> clientConsumers.handleOrderProductCreated(event));
    }

    @Test
    void shouldHandleOrderProductCreatedEventWithNullValues() {
        // Given
        OrderProductCreatedEvent event = new OrderProductCreatedEvent(
            null,
            null,
            null,
            0,
            null,
            null,
            0
        );

        // When & Then - ne doit pas lever d'exception
        assertDoesNotThrow(() -> clientConsumers.handleOrderProductCreated(event));
    }
}
