package com.sportstore.productorderingservice;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.sportstore.productorderingservice.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.sportstore.productorderingservice.model.dto.CartDTO;
import com.sportstore.productorderingservice.model.dto.OrdersDTO;

import org.springframework.amqp.support.AmqpHeaders;

import org.springframework.messaging.handler.annotation.Header;


@Component
public class OrderCreateEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATE)
    public void onMessageReceived(CartDTO message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Message received!: " + message);

        //TODO create order from cartDTO and save

        try {
            channel.basicAck(tag, false);
        } catch (Exception e) {
            channel.basicNack(tag, false, true);
        }
    }
}
