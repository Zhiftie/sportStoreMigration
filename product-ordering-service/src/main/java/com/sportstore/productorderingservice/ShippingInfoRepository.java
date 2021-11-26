package com.sportstore.productorderingservice;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sportstore.productorderingservice.model.db.ShippingInfo;

public interface ShippingInfoRepository extends PagingAndSortingRepository<ShippingInfo, Long> {

}
