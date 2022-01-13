package com.sportstore.tenantyeventservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportstore.tenantyeventservice.eventbus.EventBusService;
import com.sportstore.tenantyeventservice.model.db.SavedEvents;
import com.sportstore.tenantyeventservice.model.event.OrderShippedEvent;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("events")
public class EventResource {

    private final SavedEventsRepository savedEventsRepository;
    private final EventBusService eventBusService;

    @PostMapping
    public void saveEvent(@RequestBody OrderShippedEvent orderShippedEvent) {
        System.out.println(orderShippedEvent);
        // Save event to DB as JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(orderShippedEvent);
            SavedEvents savedEvents = new SavedEvents();
            savedEvents.setJson(json);
            savedEventsRepository.save(savedEvents);
            //TODO
            //eventBusService.publishEvent();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Publish OrderShippedSavedEvent

        // Handle RFID scanned event, republish saved event
    }

}
