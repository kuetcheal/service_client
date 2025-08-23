package com.javathinked.example.demo_spring.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@org.springframework.context.annotation.Profile("!test")


@Configuration
public class RabbitConfigClient {

    public static final String EXCHANGE = "microservice_exchange";

    public static final String Q_FROM_ORDER = "q.client.order";
    public static final String Q_FROM_PRODUCT = "q.client.product";
    public static final String Q_FROM_ORDER_PRODUCT = "q.client.orderproduct"; 

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    // Queue pour order.*
    @Bean
    public Queue orderQueue() {
        return new Queue(Q_FROM_ORDER);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with("order.*");
    }

    // Queue pour product.*
    @Bean
    public Queue productQueue() {
        return new Queue(Q_FROM_PRODUCT);
    }

    @Bean
    public Binding productBinding(Queue productQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productQueue).to(exchange).with("product.*");
    }

    //   queue pour order_product.*
    @Bean
    public Queue orderProductQueue() {
        return new Queue(Q_FROM_ORDER_PRODUCT);
    }

    @Bean
    public Binding orderProductBinding(Queue orderProductQueue, TopicExchange exchange) {
        return BindingBuilder.bind(orderProductQueue).to(exchange).with("order_product.*");
    }
}
