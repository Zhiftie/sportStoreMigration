package com.sportstore.shoppingcartservice.model.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private String description;
	private String category;
	private double price;
	private int productID;
	private String name;
}
