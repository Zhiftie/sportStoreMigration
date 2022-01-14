package com.sportstore.tenantyeventservice;


import static com.sportstore.tenantyeventservice.config.RabbitMQConfig.SHIPPING_EXCHANGE;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.sportstore.tenantyeventservice.config.RabbitMQConfig;
import com.sportstore.tenantyeventservice.eventbus.EventBusService;
import com.sportstore.tenantyeventservice.model.db.SavedEvents;
import com.sportstore.tenantyeventservice.model.event.OrderShippedEvent;
import com.sportstore.tenantyeventservice.model.event.RFIDTagScannedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RFIDTagScannedEventConsumer {

    private final SavedEventsRepository savedEventsRepository;
    private final EventBusService eventBusService;
    @RabbitListener(queues = RabbitMQConfig.RFID_TAG_SCANNED_EVENT, containerFactory = "v2ContainerFactory")
    public void onMessageReceivedTenantY(RFIDTagScannedEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        Optional<SavedEvents> savedEventsOptional = savedEventsRepository.findById(message.getSavedEventId());
        if (savedEventsOptional.isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                OrderShippedEvent orderShippedEvent = objectMapper.readValue(savedEventsOptional.get().getJson(), OrderShippedEvent.class);
                orderShippedEvent.setDoNotCheckForCustomisation(true);
                eventBusService.publishEvent(SHIPPING_EXCHANGE, orderShippedEvent);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

}
