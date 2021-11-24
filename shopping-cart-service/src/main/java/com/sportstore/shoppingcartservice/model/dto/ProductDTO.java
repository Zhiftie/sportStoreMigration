package com.sportstore.shoppingcartservice.model.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String category;

}
