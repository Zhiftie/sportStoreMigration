package com.sportstore.tenantxshippinginformation.model.db;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "shipping_information")
public class ShippingInformation {
    @Id
    @SequenceGenerator(name="shipping_information_shipping_information_id_seq",
            sequenceName="shipping_information_shipping_information_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="shipping_information_shipping_information_id_seq")
    @Column(name = "shipping_information_id")
    private Long shippingInformationId;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "shipping_time")
    private LocalDateTime shippingTime;

    @Column(name = "order_id")
    private Long orderId;
}
