package com.javathinked.example.demo_spring.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
@EnableRabbit
public class RabbitConfigClient {

    // === Exchange commun à tout le SI ===
    public static final String EXCHANGE = "app.exchange";

    // === Queues écoutées par le SERVICE CLIENT ===
    public static final String Q_FROM_ORDER         = "q.client.from-order";
    public static final String Q_FROM_PRODUCT       = "q.client.from-product";
    public static final String Q_FROM_ORDER_PRODUCT = "q.client.from-order-product";

    // === Patterns de routage ===
    public static final String RK_ORDER_ALL          = "order.*";
    public static final String RK_PRODUCT_ALL        = "product.*";
    public static final String RK_ORDER_PRODUCT_ALL  = "order.product.*";   // recommandé
    public static final String RK_ORDER_PRODUCT_ALL_LEGACY = "order_product.*"; // compat si déjà utilisé

    @Bean
    public TopicExchange appExchange() {
        // durable=true, autoDelete=false
        return new TopicExchange(EXCHANGE, true, false);
    }

    // ---------- ORDER ----------
    @Bean(name = "qClientFromOrder")
    public Queue qClientFromOrder() {
        return new Queue(Q_FROM_ORDER, true); // durable
    }

    @Bean
    public Binding bindClientFromOrder(@Qualifier("qClientFromOrder") Queue q, TopicExchange appExchange) {
        return BindingBuilder.bind(q).to(appExchange).with(RK_ORDER_ALL);
    }

    // ---------- PRODUCT ----------
    @Bean(name = "qClientFromProduct")
    public Queue qClientFromProduct() {
        return new Queue(Q_FROM_PRODUCT, true);
    }

    @Bean
    public Binding bindClientFromProduct(@Qualifier("qClientFromProduct") Queue q, TopicExchange appExchange) {
        return BindingBuilder.bind(q).to(appExchange).with(RK_PRODUCT_ALL);
    }

    // ---------- ORDER_PRODUCT ----------
    @Bean(name = "qClientFromOrderProduct")
    public Queue qClientFromOrderProduct() {
        return new Queue(Q_FROM_ORDER_PRODUCT, true);
    }

    // Binding “propre” (dot-notation)
    @Bean
    public Binding bindClientFromOrderProduct(@Qualifier("qClientFromOrderProduct") Queue q, TopicExchange appExchange) {
        return BindingBuilder.bind(q).to(appExchange).with(RK_ORDER_PRODUCT_ALL);
    }

    // Binding de compat si tu publies encore en "order_product.*"
    @Bean
    public Binding bindClientFromOrderProductLegacy(@Qualifier("qClientFromOrderProduct") Queue q, TopicExchange appExchange) {
        return BindingBuilder.bind(q).to(appExchange).with(RK_ORDER_PRODUCT_ALL_LEGACY);
    }

    // ---------- JSON converter + template ----------
    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonConverter) {
        RabbitTemplate tpl = new RabbitTemplate(connectionFactory);
        tpl.setMessageConverter(jsonConverter);
        return tpl;
    }
}
