package com.sportstore.shoppingcartservice.model.db;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "cart_line")
public class CartLine {

    @Id
    @Column(name = "cart_line_id")
    private Long cartLineId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

}
