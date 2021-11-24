package com.sportstore.shoppingcartservice.order;

import lombok.Data;

@Data
public class OrderLineDTO {
    private Long orderLineId;
    private Long productId;
    private Long quantity;
    private Long orderId;
}
