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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RabbitConfigClientAdvancedTest {

    private RabbitConfigClient rabbitConfigClient;

    @BeforeEach
    void setUp() {
        rabbitConfigClient = new RabbitConfigClient();
    }

    @Test
    void shouldCreateAppExchangeWithCorrectProperties() {
        // When
        TopicExchange exchange = rabbitConfigClient.appExchange();

        // Then
        assertNotNull(exchange);
        assertEquals(RabbitConfigClient.EXCHANGE, exchange.getName());
        assertTrue(exchange.isDurable());
        assertFalse(exchange.isAutoDelete());
        assertFalse(exchange.isInternal());
    }

    @Test
    void shouldCreateQClientFromOrderWithCorrectProperties() {
        // When
        Queue queue = rabbitConfigClient.qClientFromOrder();

        // Then
        assertNotNull(queue);
        assertEquals(RabbitConfigClient.Q_FROM_ORDER, queue.getName());
        assertTrue(queue.isDurable());
        assertFalse(queue.isAutoDelete());
        assertFalse(queue.isExclusive());
    }

    @Test
    void shouldCreateQClientFromProductWithCorrectProperties() {
        // When
        Queue queue = rabbitConfigClient.qClientFromProduct();

        // Then
        assertNotNull(queue);
        assertEquals(RabbitConfigClient.Q_FROM_PRODUCT, queue.getName());
        assertTrue(queue.isDurable());
        assertFalse(queue.isAutoDelete());
        assertFalse(queue.isExclusive());
    }

    @Test
    void shouldCreateQClientFromOrderProductWithCorrectProperties() {
        // When
        Queue queue = rabbitConfigClient.qClientFromOrderProduct();

        // Then
        assertNotNull(queue);
        assertEquals(RabbitConfigClient.Q_FROM_ORDER_PRODUCT, queue.getName());
        assertTrue(queue.isDurable());
        assertFalse(queue.isAutoDelete());
        assertFalse(queue.isExclusive());
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
    void shouldCreateRabbitTemplateWithCorrectProperties() {
        // Given
        ConnectionFactory mockConnectionFactory = mock(ConnectionFactory.class);
        MessageConverter mockMessageConverter = mock(MessageConverter.class);

        // When
        RabbitTemplate rabbitTemplate = rabbitConfigClient.rabbitTemplate(mockConnectionFactory, mockMessageConverter);

        // Then
        assertNotNull(rabbitTemplate);
        assertEquals(mockMessageConverter, rabbitTemplate.getMessageConverter());
        assertEquals(mockConnectionFactory, rabbitTemplate.getConnectionFactory());
    }

    @Test
    void shouldCreateRabbitTemplateWithNullConnectionFactory() {
        // Given
        ConnectionFactory nullConnectionFactory = null;
        MessageConverter mockMessageConverter = mock(MessageConverter.class);

        // When
        RabbitTemplate rabbitTemplate = rabbitConfigClient.rabbitTemplate(nullConnectionFactory, mockMessageConverter);

        // Then
        assertNotNull(rabbitTemplate);
        assertEquals(mockMessageConverter, rabbitTemplate.getMessageConverter());
        assertNull(rabbitTemplate.getConnectionFactory());
    }

    @Test
    void shouldCreateRabbitTemplateWithNullMessageConverter() {
        // Given
        ConnectionFactory mockConnectionFactory = mock(ConnectionFactory.class);
        MessageConverter nullMessageConverter = null;

        // When
        RabbitTemplate rabbitTemplate = rabbitConfigClient.rabbitTemplate(mockConnectionFactory, nullMessageConverter);

        // Then
        assertNotNull(rabbitTemplate);
        assertNull(rabbitTemplate.getMessageConverter());
        assertEquals(mockConnectionFactory, rabbitTemplate.getConnectionFactory());
    }

    @Test
    void shouldCreateRabbitTemplateWithBothNullParameters() {
        // Given
        ConnectionFactory nullConnectionFactory = null;
        MessageConverter nullMessageConverter = null;

        // When
        RabbitTemplate rabbitTemplate = rabbitConfigClient.rabbitTemplate(nullConnectionFactory, nullMessageConverter);

        // Then
        assertNotNull(rabbitTemplate);
        assertNull(rabbitTemplate.getMessageConverter());
        assertNull(rabbitTemplate.getConnectionFactory());
    }

    @Test
    void shouldHaveConsistentExchangeName() {
        // When
        TopicExchange exchange1 = rabbitConfigClient.appExchange();
        TopicExchange exchange2 = rabbitConfigClient.appExchange();

        // Then
        assertEquals(exchange1.getName(), exchange2.getName());
        assertEquals(RabbitConfigClient.EXCHANGE, exchange1.getName());
    }

    @Test
    void shouldHaveConsistentQueueNames() {
        // When
        Queue queue1 = rabbitConfigClient.qClientFromOrder();
        Queue queue2 = rabbitConfigClient.qClientFromProduct();
        Queue queue3 = rabbitConfigClient.qClientFromOrderProduct();

        // Then
        assertNotEquals(queue1.getName(), queue2.getName());
        assertNotEquals(queue1.getName(), queue3.getName());
        assertNotEquals(queue2.getName(), queue3.getName());
        
        assertEquals(RabbitConfigClient.Q_FROM_ORDER, queue1.getName());
        assertEquals(RabbitConfigClient.Q_FROM_PRODUCT, queue2.getName());
        assertEquals(RabbitConfigClient.Q_FROM_ORDER_PRODUCT, queue3.getName());
    }

    @Test
    void shouldCreateMultipleInstancesOfSameComponent() {
        // When
        TopicExchange exchange1 = rabbitConfigClient.appExchange();
        TopicExchange exchange2 = rabbitConfigClient.appExchange();

        // Then
        // Les instances sont différentes mais ont les mêmes propriétés
        assertNotSame(exchange1, exchange2);
        assertEquals(exchange1.getName(), exchange2.getName());
        assertEquals(exchange1.isDurable(), exchange2.isDurable());
        assertEquals(exchange1.isAutoDelete(), exchange2.isAutoDelete());
    }

    @Test
    void shouldCreateMultipleInstancesOfQueues() {
        // When
        Queue queue1 = rabbitConfigClient.qClientFromOrder();
        Queue queue2 = rabbitConfigClient.qClientFromOrder();

        // Then
        // Les instances sont différentes mais ont les mêmes propriétés
        assertNotSame(queue1, queue2);
        assertEquals(queue1.getName(), queue2.getName());
        assertEquals(queue1.isDurable(), queue2.isDurable());
        assertEquals(queue1.isAutoDelete(), queue2.isAutoDelete());
    }
}
