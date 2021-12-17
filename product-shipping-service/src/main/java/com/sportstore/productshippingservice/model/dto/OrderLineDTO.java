package com.sportstore.productshippingservice.model.dto;

import lombok.Data;

@Data
public class OrderLineDTO {

    private Long orderLineId;
    private Long productId;
    private String productName;
    private Long quantity;
    private Long orderId;
}
