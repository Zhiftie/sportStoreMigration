package com.sportstore.productorderingservice.config;

import static com.sportstore.productorderingservice.config.RabbitMQConfig.CART_CHECKOUT_EXCHANGE;
import static com.sportstore.productorderingservice.config.RabbitMQConfig.CART_CHECKOUT_EVENT;
import static com.sportstore.productorderingservice.config.RabbitMQConfig.ORDER_CREATED_EXCHANGE;
import static com.sportstore.productorderingservice.config.RabbitMQConfig.ORDER_SHIPPED_EVENT;
import static com.sportstore.productorderingservice.config.RabbitMQConfig.SHIPPING_EXCHANGE;

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
        v2RabbitAdmin.declareQueue(new Queue(CART_CHECKOUT_EVENT, false));
        v2RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(CART_CHECKOUT_EVENT, false))
                        .to(new FanoutExchange(CART_CHECKOUT_EXCHANGE, true, true)));

        v1RabbitAdmin.declareQueue(new Queue(CART_CHECKOUT_EVENT, false));
        v1RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(CART_CHECKOUT_EVENT, false))
                        .to(new FanoutExchange(CART_CHECKOUT_EXCHANGE, true, false)));

        v2RabbitAdmin.declareQueue(new Queue(ORDER_SHIPPED_EVENT, false));
        v2RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(ORDER_SHIPPED_EVENT, false))
                        .to(new FanoutExchange(SHIPPING_EXCHANGE, true, true)));

        v1RabbitAdmin.declareQueue(new Queue(ORDER_SHIPPED_EVENT, false));
        v1RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(ORDER_SHIPPED_EVENT, false))
                        .to(new FanoutExchange(SHIPPING_EXCHANGE, true, false)));


        v2RabbitAdmin.declareExchange(new FanoutExchange(ORDER_CREATED_EXCHANGE, true, false));
        v1RabbitAdmin.declareExchange(new FanoutExchange(ORDER_CREATED_EXCHANGE, true, false));
    }
}
