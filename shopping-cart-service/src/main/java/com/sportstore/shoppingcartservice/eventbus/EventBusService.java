package com.sportstore.shoppingcartservice.eventbus;

public interface EventBusService {

    void publishEvent(String exchange, Event event);

}
