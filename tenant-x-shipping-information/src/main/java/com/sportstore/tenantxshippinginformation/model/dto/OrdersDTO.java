package com.sportstore.tenantxshippinginformation.model.dto;

import java.util.Set;

import lombok.Data;

@Data
public class OrdersDTO {
    private Long orderId;
    private String customerId;
    private double totalCost;
    private Set<OrderLineDTO> orderLines;
    private ShippingInfoDTO shippingInfo;
}
