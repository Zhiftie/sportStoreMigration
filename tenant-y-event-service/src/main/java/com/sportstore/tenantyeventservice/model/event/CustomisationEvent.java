package com.sportstore.tenantyeventservice.model.event;


import com.sportstore.tenantyeventservice.eventbus.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomisationEvent extends Event {
    private String json;
}
