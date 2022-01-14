package com.sportstore.tenantyrfidservice.model.db;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "rfid_tag")
public class RfidTag {
    @Id
    @SequenceGenerator(name="rfid_tag_rfid_id_seq",
            sequenceName="rfid_tag_rfid_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="rfid_tag_rfid_id_seq")
    @Column(name = "rfid_id")
    private Long rfidId;

    @Column(name = "saved_event_id")
    private Long savedEventId;

}
