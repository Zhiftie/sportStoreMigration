package com.sportstore.tenantmanagerservice;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "customisations")
public class Customisations {
    @Id
    @SequenceGenerator(name="customisations_customisation_id_seq",
            sequenceName="customisations_customisation_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="customisations_customisation_id_seq")
    @Column(name = "customisation_id")
    private Long customisationId;

    @Column(name = "tenant_name")
    private String tenantName;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "endpoint")
    private String endpoint;

}
