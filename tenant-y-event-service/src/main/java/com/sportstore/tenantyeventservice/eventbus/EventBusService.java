package com.sportstore.tenantyeventservice.eventbus;

public interface EventBusService {

    void publishEvent(String exchange, Event event);

}
