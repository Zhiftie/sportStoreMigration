package com.sportstore.tenantyrfidservice.event;

import lombok.Data;

@Data
public abstract class Event {
    private String tenant;
    private String name;
    private boolean doNotCheckForCustomisation;
}

