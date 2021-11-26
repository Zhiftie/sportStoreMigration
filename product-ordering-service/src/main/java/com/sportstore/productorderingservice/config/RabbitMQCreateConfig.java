package com.sportstore.productorderingservice.config;

import static com.sportstore.productorderingservice.config.RabbitMQConfig.EXCHANGE;
import static com.sportstore.productorderingservice.config.RabbitMQConfig.ORDER_CREATE;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
        v2RabbitAdmin.declareQueue(new Queue(ORDER_CREATE, false));
        v2RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(ORDER_CREATE, false))
                        .to(new FanoutExchange(EXCHANGE, true, true)));

        v1RabbitAdmin.declareQueue(new Queue(ORDER_CREATE, false));
        v1RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(ORDER_CREATE, false))
                        .to(new FanoutExchange(EXCHANGE, true, false)));
    }
}
