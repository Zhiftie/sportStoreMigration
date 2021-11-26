package com.sportstore.tenantxshippinginformation;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sportstore.tenantxshippinginformation.model.db.ShippingInformation;

public interface ShippingInformationRepository extends PagingAndSortingRepository<ShippingInformation, Long> {

}
