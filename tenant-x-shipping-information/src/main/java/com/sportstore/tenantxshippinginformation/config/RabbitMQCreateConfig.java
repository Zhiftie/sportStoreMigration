package com.sportstore.tenantxshippinginformation.config;

import static com.sportstore.tenantxshippinginformation.config.RabbitMQConfig.ORDER_CREATED_EXCHANGE;
import static com.sportstore.tenantxshippinginformation.config.RabbitMQConfig.ORDER_CREATED;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQCreateConfig {


    @Resource(name = "v1RabbitAdmin")
    private RabbitAdmin v1RabbitAdmin;

    @PostConstruct
    public void init() {
        v1RabbitAdmin.declareQueue(new Queue(ORDER_CREATED, false));
        v1RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(ORDER_CREATED, false))
                        .to(new FanoutExchange(ORDER_CREATED_EXCHANGE, true, false)));
    }
}
