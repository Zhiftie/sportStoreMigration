package com.sportstore.productorderingservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "order")
public class Order {

    /*@Id
    @SequenceGenerator(name="product_catalog_product_id_seq",
            sequenceName="product_catalog_product_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="product_catalog_product_id_seq")*/
    @Id
    private Long OrderID;
    private String customerId;
    private double totalCost;

    //TODO add lines and shipping info
}
