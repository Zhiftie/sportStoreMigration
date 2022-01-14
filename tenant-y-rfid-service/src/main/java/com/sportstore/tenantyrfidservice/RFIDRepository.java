package com.sportstore.tenantyrfidservice;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sportstore.tenantyrfidservice.model.db.RfidTag;

public interface RFIDRepository extends PagingAndSortingRepository<RfidTag, Long> {
    RfidTag findBySavedEventId(Long savedEventId);
}
