package com.sportstore.tenantmanagerservice;


import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomisationsRepository extends PagingAndSortingRepository<Customisations, Long> {
    Optional<Customisations> findFirstByTenantNameAndEventNameEquals(String tenantName, String eventName);
}
