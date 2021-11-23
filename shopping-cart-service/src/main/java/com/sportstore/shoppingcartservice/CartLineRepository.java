package com.sportstore.shoppingcartservice;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartLineRepository extends PagingAndSortingRepository<CartLine, Long> {

}
