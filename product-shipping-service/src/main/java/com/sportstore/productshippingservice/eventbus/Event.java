package com.sportstore.productshippingservice.eventbus;

import lombok.Data;

@Data
public abstract class Event {

    private String tenant;
    private String name;
}
