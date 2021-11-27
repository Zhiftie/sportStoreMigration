package com.sportstore.productorderingservice.model.events;

import com.sportstore.productorderingservice.eventbus.Event;
import com.sportstore.productorderingservice.model.dto.OrdersDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderCreatedEvent extends Event {
    private OrdersDTO ordersDTO;
}
