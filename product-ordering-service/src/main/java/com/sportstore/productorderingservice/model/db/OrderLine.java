package com.sportstore.productorderingservice.model.db;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "order_line")
public class OrderLine {
    @Id
    @Column(name = "order_line_id")
    private Long orderLineId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable =  false, updatable = false)
    private Order order;
}
