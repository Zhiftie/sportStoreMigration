package com.sportstore.tenantyeventservice;

import static com.sportstore.tenantyeventservice.config.RabbitMQConfig.SHIPPING_EXCHANGE;
import static com.sportstore.tenantyeventservice.config.RabbitMQConfig.TENANT_Y_EVENT_EXCHANGE;

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
import com.sportstore.tenantyeventservice.model.dto.SavedEventsDTO;
import com.sportstore.tenantyeventservice.model.event.CustomisationEvent;
import com.sportstore.tenantyeventservice.model.event.OrderShippedEvent;
import com.sportstore.tenantyeventservice.model.event.OrderShippedSavedEvent;
import com.sportstore.tenantyeventservice.model.event.RFIDTagScannedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomisationEventConsumer {

    private final SavedEventsRepository savedEventsRepository;
    private final EventBusService eventBusService;

    @RabbitListener(queues = RabbitMQConfig.CUSTOMISATION_EVENT, containerFactory = "v2ContainerFactory")
    public void onMessageReceivedTenantY(CustomisationEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        SavedEvents savedEvents = new SavedEvents();
        savedEvents.setJson(message.getJson());
        savedEventsRepository.save(savedEvents);
        SavedEventsDTO savedEventsDTO = new SavedEventsDTO();
        savedEventsDTO.setSavedEventId(savedEvents.getSavedEventId());
        savedEventsDTO.setJson(savedEvents.getJson());
        OrderShippedSavedEvent orderShippedSavedEvent = new OrderShippedSavedEvent();
        orderShippedSavedEvent.setSavedEvents(savedEventsDTO);
        orderShippedSavedEvent.setName(OrderShippedSavedEvent.class.getSimpleName());
        orderShippedSavedEvent.setTenant(message.getTenant());
        eventBusService.publishEvent(TENANT_Y_EVENT_EXCHANGE, orderShippedSavedEvent);
    }
}
