package com.sportstore.productorderingservice.model.db;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "shipping_info")
public class ShippingInfo {

    @Id
    @SequenceGenerator(name="shipping_info_shipping_info_id_seq",
            sequenceName="shipping_info_shipping_info_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="shipping_info_shipping_info_id_seq")
    @Column(name = "shipping_info_id")
    private Long shippingInfoId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "name")
    private String name;

    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "line3")
    private String line3;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "gift_wrap")
    private Boolean giftWrap;

    @Column(name = "shipped")
    private Boolean shipped;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable =  false, updatable = false)
    private Order order;
}
