package com.sportstore.shoppingcartservice.config;

import static com.sportstore.shoppingcartservice.config.RabbitMQConfig.EXCHANGE;
import static com.sportstore.shoppingcartservice.config.RabbitMQConfig.ORDER_CREATE;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQCreateConfig {

    @Resource(name = "v2RabbitAdmin")
    private RabbitAdmin v2RabbitAdmin;

    @Resource(name = "v1RabbitAdmin")
    private RabbitAdmin v1RabbitAdmin;

    @PostConstruct
    public void init() {
        v2RabbitAdmin.declareExchange(new TopicExchange(EXCHANGE, true, false));
        v2RabbitAdmin.declareQueue(new Queue(ORDER_CREATE, true));
        v2RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(ORDER_CREATE, true))
                        .to(new TopicExchange(EXCHANGE, true, false))
                        .with("tenanty.key"));

        v1RabbitAdmin.declareExchange(new TopicExchange(EXCHANGE, true, false));
        v1RabbitAdmin.declareQueue(new Queue(ORDER_CREATE, true));
        v1RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(ORDER_CREATE, true))
                        .to(new TopicExchange(EXCHANGE, true, false))
                        .with("tenantx.key"));
    }
}
