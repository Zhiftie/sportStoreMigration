package com.sportstore.productshippingservice.model.event;

import com.sportstore.productshippingservice.eventbus.Event;
import com.sportstore.productshippingservice.model.dto.OrdersDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderShippedEvent extends Event {

    private boolean orderShipped;
    private OrdersDTO ordersDTO;
}