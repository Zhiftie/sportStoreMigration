package com.sportstore.tenantyrfidservice;



import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.sportstore.tenantyrfidservice.config.RabbitMQConfig;
import com.sportstore.tenantyrfidservice.model.db.RfidTag;
import com.sportstore.tenantyrfidservice.model.event.OrderShippedSavedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderShippedSavedEventConsumer {

    private final RFIDRepository rfidRepository;

    @RabbitListener(queues = RabbitMQConfig.ORDER_SHIPPED_SAVED_EVENT, containerFactory = "v2ContainerFactory")
    public void onMessageReceivedTenantY(OrderShippedSavedEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        RfidTag rfidTag = new RfidTag();
        rfidTag.setSavedEventId(message.getSavedEvents().getSavedEventId());
        rfidRepository.save(rfidTag);
    }

}
