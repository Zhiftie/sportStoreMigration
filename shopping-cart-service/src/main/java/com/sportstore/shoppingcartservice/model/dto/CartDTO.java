package com.sportstore.shoppingcartservice.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class CartDTO {
	private String zip;
	private boolean giftWrap;
	private String state;
	private String country;
	private boolean shipped;
	private String city;
	private String line1;
	private String line2;
	private List<CartLineDTO> lines;
	private String name;
	private String line3;
	private double totalCost;
}
