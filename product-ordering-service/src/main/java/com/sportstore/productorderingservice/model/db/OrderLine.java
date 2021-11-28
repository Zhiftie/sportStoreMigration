package com.sportstore.productorderingservice.model.db;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "order_line")
public class OrderLine {
    @Id
    @SequenceGenerator(name="order_line_order_line_id_seq",
            sequenceName="order_line_order_line_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="order_line_order_line_id_seq")
    @Column(name = "order_line_id")
    private Long orderLineId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable =  false, updatable = false)
    private Order order;
}
