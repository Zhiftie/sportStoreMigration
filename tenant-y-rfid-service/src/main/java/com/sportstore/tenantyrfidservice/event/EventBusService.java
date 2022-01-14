package com.sportstore.tenantyrfidservice.event;

public interface EventBusService {

    void publishEvent(String exchange, Event event);

}
