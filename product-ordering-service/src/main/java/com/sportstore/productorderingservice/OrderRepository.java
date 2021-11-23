package com.sportstore.productorderingservice;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.sportstore.productorderingservice.model.db.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

}

