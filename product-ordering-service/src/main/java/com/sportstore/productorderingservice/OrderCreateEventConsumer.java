package com.sportstore.productorderingservice;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.sportstore.productorderingservice.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.sportstore.productorderingservice.model.dto.CartDTO;

import org.springframework.amqp.support.AmqpHeaders;

import org.springframework.messaging.handler.annotation.Header;

@Component
public class OrderCreateEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATE, containerFactory = "v2ContainerFactory" )
    public void onMessageReceivedTenantY(CartDTO message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println("Message received tenantY!: " + message);
        handleOrderCreate(message);
        //TODO create order from cartDTO and save
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATE, containerFactory = "v1ContainerFactory" )
    public void onMessageReceivedTenantX(CartDTO message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println("Message received tenantX!: " + message);
        handleOrderCreate(message);
        //TODO create order from cartDTO and save
    }

    private void handleOrderCreate(CartDTO cartDTO) {
        // TODO
    }
}
