package com.sportstore.productorderingservice.eventbus;

public interface EventBusService {

    void publishEvent(String exchange, Event event);

}
