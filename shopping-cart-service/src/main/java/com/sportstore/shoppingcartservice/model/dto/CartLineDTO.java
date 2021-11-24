package com.sportstore.shoppingcartservice.model.dto;

import lombok.Data;

@Data
public class CartLineDTO {
    private Long cartLineId;
    private Long quantity;
    private ProductDTO product;
}
