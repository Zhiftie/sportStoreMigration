package com.sportstore.tenantxshippinginformation;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.sportstore.tenantxshippinginformation.config.RabbitMQConfig;
import com.sportstore.tenantxshippinginformation.model.dto.OrdersDTO;

@Component
public class OrderCreateEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED, containerFactory = "v1ContainerFactory" )
    public void onMessageReceivedTenantX(OrdersDTO message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println("Message received tenantX!: " + message);
        handleOrderCreate(message);
        //TODO create order from cartDTO and save
    }

    private void handleOrderCreate(OrdersDTO cartDTO) {
        // TODO
    }
}
