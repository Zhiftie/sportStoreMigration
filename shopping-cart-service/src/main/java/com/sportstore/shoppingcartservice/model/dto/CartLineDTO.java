package com.sportstore.shoppingcartservice.model.dto;

import lombok.Data;

@Data
public class CartLineDTO {
	private int cartLineID;
	private ProductDTO product;
	private int quantity;
}
