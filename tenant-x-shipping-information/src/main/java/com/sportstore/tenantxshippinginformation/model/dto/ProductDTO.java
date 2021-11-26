package com.sportstore.tenantxshippinginformation.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDTO {
	@JsonProperty("Description")
	private String description;
	@JsonProperty("Category")
	private String category;
	@JsonProperty("Price")
	private double price;
	@JsonProperty("ProductID")
	private int productID;
	@JsonProperty("Name")
	private String name;
}
