package com.sportstore.shoppingcartservice.config;

import static com.sportstore.shoppingcartservice.config.RabbitMQConfig.CART_CHECKOUT_EXCHANGE;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.core.FanoutExchange;
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
        v2RabbitAdmin.declareExchange(new FanoutExchange(CART_CHECKOUT_EXCHANGE, true, false));
        v1RabbitAdmin.declareExchange(new FanoutExchange(CART_CHECKOUT_EXCHANGE, true, false));
    }
}
