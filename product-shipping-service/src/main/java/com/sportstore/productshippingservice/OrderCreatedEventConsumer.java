package com.sportstore.productshippingservice;


import static com.sportstore.productshippingservice.config.RabbitMQConfig.SHIPPING_EXCHANGE;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.sportstore.productshippingservice.config.RabbitMQConfig;
import com.sportstore.productshippingservice.eventbus.EventBusService;
import com.sportstore.productshippingservice.model.event.OrderCreatedEvent;
import com.sportstore.productshippingservice.model.event.OrderShippedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventConsumer {

    private final EventBusService eventBusService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_EVENT, containerFactory = "v2ContainerFactory")
    public void onMessageReceivedTenantY(OrderCreatedEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        handleOrderCreatedEvent(message);
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_EVENT, containerFactory = "v1ContainerFactory")
    public void onMessageReceivedTenantX(OrderCreatedEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        handleOrderCreatedEvent(message);
    }

    private void handleOrderCreatedEvent(OrderCreatedEvent message) {
        OrderShippedEvent orderShippedEvent = new OrderShippedEvent();
        orderShippedEvent.setName(OrderShippedEvent.class.getSimpleName());
        orderShippedEvent.setTenant(message.getTenant());
        orderShippedEvent.setOrderShipped(true);
        orderShippedEvent.setOrdersDTO(message.getOrdersDTO());
        eventBusService.publishEvent(SHIPPING_EXCHANGE, orderShippedEvent);
    }
}
