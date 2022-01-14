package com.sportstore.tenantyeventservice.model.db;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "saved_events")
public class SavedEvents {
    @Id
    @SequenceGenerator(name="saved_events_saved_event_id_seq",
            sequenceName="saved_events_saved_event_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="saved_events_saved_event_id_seq")
    @Column(name = "saved_event_id")
    private Long savedEventId;

    @Column(name = "json")
    private String json;
}
