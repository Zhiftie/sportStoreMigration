package com.sportstore.shoppingcartservice.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CartDTO {
	@JsonProperty("Zip")
	private String zip;
	@JsonProperty("GiftWrap")
	private boolean giftWrap;
	@JsonProperty("State")
	private String state;
	@JsonProperty("Country")
	private String country;
	@JsonProperty("Shipped")
	private boolean shipped;
	@JsonProperty("City")
	private String city;
	@JsonProperty("Line1")
	private String line1;
	@JsonProperty("Line2")
	private String line2;
	@JsonProperty("Lines")
	private List<CartLineDTO> lines;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Line3")
	private String line3;
	private double totalCost;
}
