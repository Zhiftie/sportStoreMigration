package com.sportstore.tenantxshippinginformation.model.dto;

import lombok.Data;

@Data
public class ShippingInfoDTO {

    private Long shippingInfoId;
    private Long orderId;
    private String name;
    private String line1;
    private String line2;
    private String line3;
    private String city;
    private String state;
    private String country;
    private Boolean giftWrap;
    private Boolean shipped;
}
