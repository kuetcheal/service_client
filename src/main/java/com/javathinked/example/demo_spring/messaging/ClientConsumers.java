package com.javathinked.example.demo_spring.messaging;

import com.javathinked.example.demo_spring.config.RabbitConfigClient;
import com.javathinked.example.demo_spring.dto.events.OrderCreatedEvent;
import com.javathinked.example.demo_spring.dto.events.ProductStockUpdatedEvent;
import com.javathinked.example.demo_spring.dto.events.OrderProductCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClientConsumers {

    @RabbitListener(queues = RabbitConfigClient.Q_FROM_ORDER)
    public void handleOrderCreated(OrderCreatedEvent evt) {
        System.out.println("ClientService <- [OrderCreatedEvent] : " + evt);
        // Traitement logique éventuel
    }

    @RabbitListener(queues = RabbitConfigClient.Q_FROM_PRODUCT)
    public void handleProductStockUpdated(ProductStockUpdatedEvent evt) {
        System.out.println("ClientService <- [ProductStockUpdatedEvent] : " + evt);
        // Traitement logique éventuel
    }

    @RabbitListener(queues = RabbitConfigClient.Q_FROM_ORDER_PRODUCT)
    public void handleOrderProductCreated(OrderProductCreatedEvent evt) {
        System.out.println("ClientService <- [OrderProductCreatedEvent] : " + evt);
        // Traitement logique éventuel
    }
}
