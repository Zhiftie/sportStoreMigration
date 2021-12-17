package com.sportstore.tenantyeventservice.model.event;

import com.sportstore.tenantyeventservice.eventbus.Event;
import com.sportstore.tenantyeventservice.model.dto.OrdersDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderShippedEvent extends Event {

    private boolean orderShipped;
    private OrdersDTO ordersDTO;
}
