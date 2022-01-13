package com.sportstore.tenantyeventservice;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sportstore.tenantyeventservice.model.db.SavedEvents;

public interface SavedEventsRepository extends PagingAndSortingRepository<SavedEvents, Long> {

}
