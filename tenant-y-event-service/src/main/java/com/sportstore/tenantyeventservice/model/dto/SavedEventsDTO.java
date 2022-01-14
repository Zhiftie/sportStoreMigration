package com.sportstore.tenantyeventservice.model.dto;

import javax.persistence.Column;

import lombok.Data;

@Data
public class SavedEventsDTO {
    private Long savedEventId;

    private String json;
}
