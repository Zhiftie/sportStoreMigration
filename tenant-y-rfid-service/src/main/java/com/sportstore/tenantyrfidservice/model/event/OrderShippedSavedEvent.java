package com.sportstore.tenantyrfidservice.model.event;


import com.sportstore.tenantyrfidservice.event.Event;
import com.sportstore.tenantyrfidservice.model.dto.SavedEventsDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderShippedSavedEvent extends Event {
    private SavedEventsDTO savedEvents;
}
