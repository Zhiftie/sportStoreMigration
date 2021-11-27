package com.sportstore.tenantxshippinginformation.model.event;


import com.sportstore.tenantxshippinginformation.model.dto.OrdersDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderCreatedEvent extends Event {
    private OrdersDTO ordersDTO;
}
