package com.sportstore.shoppingcartservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CartLineDTO {
	@JsonProperty("CartLineID")
	private int cartLineID;
	@JsonProperty("Product")
	private ProductDTO product;
	@JsonProperty("Quantity")
	private int quantity;
}
