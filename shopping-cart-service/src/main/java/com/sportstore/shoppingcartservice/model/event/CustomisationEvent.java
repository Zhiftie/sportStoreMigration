package com.sportstore.shoppingcartservice.model.event;


import com.sportstore.shoppingcartservice.eventbus.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomisationEvent extends Event {

    private String json;
}
