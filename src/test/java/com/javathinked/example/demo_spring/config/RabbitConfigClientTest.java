package com.javathinked.example.demo_spring.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RabbitConfigClientTest {

    private RabbitConfigClient rabbitConfigClient;

    @BeforeEach
    void setUp() {
        rabbitConfigClient = new RabbitConfigClient();
    }

    @Test
    void shouldCreateAppExchange() {
        // When
        TopicExchange exchange = rabbitConfigClient.appExchange();

        // Then
        assertNotNull(exchange);
        assertEquals("app.exchange", exchange.getName());
        assertTrue(exchange.isDurable());
        assertFalse(exchange.isAutoDelete());
    }

    @Test
    void shouldCreateClientFromOrderQueue() {
        // When
        Queue queue = rabbitConfigClient.qClientFromOrder();

        // Then
        assertNotNull(queue);
        assertEquals("q.client.from-order", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void shouldCreateClientFromProductQueue() {
        // When
        Queue queue = rabbitConfigClient.qClientFromProduct();

        // Then
        assertNotNull(queue);
        assertEquals("q.client.from-product", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void shouldCreateClientFromOrderProductQueue() {
        // When
        Queue queue = rabbitConfigClient.qClientFromOrderProduct();

        // Then
        assertNotNull(queue);
        assertEquals("q.client.from-order-product", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void shouldCreateJsonConverter() {
        // When
        MessageConverter converter = rabbitConfigClient.jsonConverter();

        // Then
        assertNotNull(converter);
        assertTrue(converter instanceof org.springframework.amqp.support.converter.Jackson2JsonMessageConverter);
    }

    @Test
    void shouldCreateRabbitTemplate() {
        // Given
        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
        MessageConverter messageConverter = mock(MessageConverter.class);

        // When
        RabbitTemplate template = rabbitConfigClient.rabbitTemplate(connectionFactory, messageConverter);

        // Then
        assertNotNull(template);
        assertEquals(connectionFactory, template.getConnectionFactory());
        assertEquals(messageConverter, template.getMessageConverter());
    }

    @Test
    void shouldHaveCorrectRoutingKeys() {
        // Then
        assertEquals("order.*", RabbitConfigClient.RK_ORDER_ALL);
        assertEquals("product.*", RabbitConfigClient.RK_PRODUCT_ALL);
        assertEquals("order.product.*", RabbitConfigClient.RK_ORDER_PRODUCT_ALL);
        assertEquals("order_product.*", RabbitConfigClient.RK_ORDER_PRODUCT_ALL_LEGACY);
    }

    @Test
    void shouldHaveCorrectExchangeName() {
        // Then
        assertEquals("app.exchange", RabbitConfigClient.EXCHANGE);
    }

    @Test
    void shouldHaveCorrectQueueNames() {
        // Then
        assertEquals("q.client.from-order", RabbitConfigClient.Q_FROM_ORDER);
        assertEquals("q.client.from-product", RabbitConfigClient.Q_FROM_PRODUCT);
        assertEquals("q.client.from-order-product", RabbitConfigClient.Q_FROM_ORDER_PRODUCT);
    }
}
