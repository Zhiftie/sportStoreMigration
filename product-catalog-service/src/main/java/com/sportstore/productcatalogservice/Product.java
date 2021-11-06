package com.sportstore.productcatalogservice;

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
@Table(name = "product_catalog")
public class Product {

    @Id
    @SequenceGenerator(name="product_catalog_product_id_seq",
            sequenceName="product_catalog_product_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="product_catalog_product_id_seq")
    @Column(name = "product_id")
    private Long productID;
    private Long price;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_category")
    private String category;
    private String description;

}
