package com.javathinked.example.demo_spring.dto.events;

import java.time.Instant;

public record OrderProductCreatedEvent(
    Long id,
    Long orderId,
    Long productId,
    int quantity,
    Instant createdAt,      // optionnel
    String source,          // "service-commande"
    int version             // 1
) {}
