package com.sportstore.productorderingservice;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportstore.productorderingservice.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.sportstore.productorderingservice.model.dto.CartDTO;

import org.springframework.amqp.support.AmqpHeaders;

import org.springframework.messaging.handler.annotation.Header;

@Component
public class OrderCreateEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATE, containerFactory = "v2ContainerFactory" )
    public void onMessageReceivedTenantY(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Message received!: " + message);
        ObjectMapper mapper = new ObjectMapper();
        CartDTO cartDTO = mapper.readValue(message, CartDTO.class);
        System.out.println(cartDTO);
        //TODO create order from cartDTO and save
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATE, containerFactory = "v1ContainerFactory" )
    public void onMessageReceivedTenantX(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Message received!: " + message);
        ObjectMapper mapper = new ObjectMapper();
        CartDTO cartDTO = mapper.readValue(message, CartDTO.class);
        System.out.println(cartDTO);
        //TODO create order from cartDTO and save
    }
}
