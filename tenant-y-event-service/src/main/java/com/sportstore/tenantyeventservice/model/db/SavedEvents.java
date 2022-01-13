package com.sportstore.tenantyeventservice.model.db;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "saved_events")
public class SavedEvents {
    @Id
    @Column(name = "saved_event_id")
    private Long savedEventId;

    @Column(name = "json")
    private String json;
}
