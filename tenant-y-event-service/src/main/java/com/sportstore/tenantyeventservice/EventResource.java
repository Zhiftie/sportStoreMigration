package com.sportstore.tenantyeventservice;

import static com.sportstore.tenantyeventservice.config.RabbitMQConfig.TENANT_Y_EVENT_EXCHANGE;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportstore.tenantyeventservice.eventbus.EventBusService;
import com.sportstore.tenantyeventservice.model.db.SavedEvents;
import com.sportstore.tenantyeventservice.model.dto.SavedEventsDTO;
import com.sportstore.tenantyeventservice.model.event.OrderShippedEvent;
import com.sportstore.tenantyeventservice.model.event.OrderShippedSavedEvent;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("events")
public class EventResource {

    private final SavedEventsRepository savedEventsRepository;
    private final EventBusService eventBusService;

    @PostMapping
    public void saveEvent(@RequestBody OrderShippedEvent orderShippedEvent) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(orderShippedEvent);
            SavedEvents savedEvents = new SavedEvents();
            savedEvents.setJson(json);
            savedEventsRepository.save(savedEvents);
            SavedEventsDTO savedEventsDTO = new SavedEventsDTO();
            savedEventsDTO.setSavedEventId(savedEvents.getSavedEventId());
            savedEventsDTO.setJson(savedEvents.getJson());
            OrderShippedSavedEvent orderShippedSavedEvent = new OrderShippedSavedEvent();
            orderShippedSavedEvent.setSavedEvents(savedEventsDTO);
            orderShippedSavedEvent.setName(OrderShippedSavedEvent.class.getSimpleName());
            orderShippedSavedEvent.setTenant(orderShippedEvent.getTenant());
            eventBusService.publishEvent(TENANT_Y_EVENT_EXCHANGE, orderShippedSavedEvent);
            System.out.println(System.currentTimeMillis());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
