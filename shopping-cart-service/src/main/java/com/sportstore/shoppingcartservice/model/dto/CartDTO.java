package com.sportstore.shoppingcartservice.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
    private List<CartLineDTO> lineCollection;
    private double totalCost;

}
