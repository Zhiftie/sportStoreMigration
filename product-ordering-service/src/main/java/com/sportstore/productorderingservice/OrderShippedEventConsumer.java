package com.sportstore.productorderingservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.sportstore.productorderingservice.config.RabbitMQConfig;
import com.sportstore.productorderingservice.model.db.Order;
import com.sportstore.productorderingservice.model.events.OrderShippedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderShippedEventConsumer {

    private final OrderRepository orderRepository;
    private final ShippingInfoRepository shippingInfoRepository;

    @RabbitListener(queues = RabbitMQConfig.ORDER_SHIPPED_EVENT, containerFactory = "v2ContainerFactory")
    public void onMessageReceivedTenantY(OrderShippedEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        handleOrderShippedEvent(message);
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_SHIPPED_EVENT, containerFactory = "v1ContainerFactory")
    public void onMessageReceivedTenantX(OrderShippedEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        handleOrderShippedEvent(message);
    }

    private void handleOrderShippedEvent(OrderShippedEvent message) {
        Optional<Order> order = orderRepository.findById(message.getOrdersDTO().getOrderId());
        order.ifPresent(value -> {
            value.getShippingInfo().setShipped(message.isOrderShipped());
            shippingInfoRepository.save(value.getShippingInfo());
        });
    }
}
