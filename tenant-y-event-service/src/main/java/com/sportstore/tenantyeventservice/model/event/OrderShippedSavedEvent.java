package com.sportstore.tenantyeventservice.model.event;

import com.sportstore.tenantyeventservice.eventbus.Event;
import com.sportstore.tenantyeventservice.model.dto.SavedEventsDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderShippedSavedEvent extends Event {
    private SavedEventsDTO savedEvents;
}
