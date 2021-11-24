package com.sportstore.shoppingcartservice;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sportstore.shoppingcartservice.model.db.CartLine;

public interface CartLineRepository extends PagingAndSortingRepository<CartLine, Long> {

}
