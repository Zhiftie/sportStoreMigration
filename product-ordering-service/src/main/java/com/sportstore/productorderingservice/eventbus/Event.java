package com.sportstore.productorderingservice.eventbus;

import lombok.Data;

@Data
public abstract class Event {
    private String tenant;
    private String name;
    private boolean doNotCheckForCustomisation;
}
