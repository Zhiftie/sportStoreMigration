package com.sportstore.tenantxshippinginformation.model.event;

import lombok.Data;

@Data
public abstract class Event {
    private String tenant;
    private String name;
}
