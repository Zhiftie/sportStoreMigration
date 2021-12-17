package com.sportstore.productshippingservice.eventbus;

public interface EventBusService {

    void publishEvent(String exchange, Event event);

}
