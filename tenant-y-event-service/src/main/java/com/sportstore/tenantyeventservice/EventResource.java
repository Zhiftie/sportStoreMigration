package com.sportstore.tenantyeventservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportstore.tenantyeventservice.model.event.OrderShippedEvent;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("events")
public class EventResource {

    @PostMapping
    public void saveEvent(@RequestBody OrderShippedEvent orderShippedEvent) {
        System.out.println(orderShippedEvent);
        // Save event to DB

        // Publish OrderShippedSavedEvent

        // Handle RFID scanned event, republish saved event
    }

}
