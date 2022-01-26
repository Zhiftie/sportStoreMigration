package com.sportstore.productshippingservice.model.event;

import com.sportstore.productshippingservice.eventbus.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomisationEvent extends Event {
    private String json;
}
