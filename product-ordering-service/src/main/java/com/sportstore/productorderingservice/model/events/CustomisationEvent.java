package com.sportstore.productorderingservice.model.events;


import com.sportstore.productorderingservice.eventbus.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomisationEvent extends Event {
    private String json;
}
