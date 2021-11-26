package com.sportstore.productorderingservice.model.db;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(name="orders_order_id_seq",
            sequenceName="orders_order_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="orders_order_id_seq")
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "total_cost")
    private double totalCost;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines;

    @OneToOne(mappedBy = "order")
    private ShippingInfo shippingInfo;

}
