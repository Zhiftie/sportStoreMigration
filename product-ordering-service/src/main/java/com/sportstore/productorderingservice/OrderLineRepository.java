package com.sportstore.productorderingservice;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sportstore.productorderingservice.model.db.OrderLine;

public interface OrderLineRepository extends PagingAndSortingRepository<OrderLine, Long> {

}
