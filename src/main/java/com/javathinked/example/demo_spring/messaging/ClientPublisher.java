package com.javathinked.example.demo_spring.messaging;

import com.javathinked.example.demo_spring.config.RabbitConfigClient;
import com.javathinked.example.demo_spring.dto.events.ClientCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientPublisher {

    private final RabbitTemplate rabbitTemplate;

    public ClientPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishClientCreated(ClientCreatedEvent evt) {
        // Les services abonnés à "client.*" recevront cet event
        rabbitTemplate.convertAndSend(RabbitConfigClient.EXCHANGE, "client.created", evt);
    }
}
