package com.sportstore.tenantyeventservice.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrdersDTO {
    private Long orderId;
    private String customerId;
    private double totalCost;
    private List<OrderLineDTO> Lines;
    private ShippingInfoDTO shippingInfo;
}
