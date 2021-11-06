package com.sportstore.productcatalogservice;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductCatalogRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByCategoryEquals(String category);

}
