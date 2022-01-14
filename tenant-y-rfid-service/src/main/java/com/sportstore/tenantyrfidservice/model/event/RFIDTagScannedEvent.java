package com.sportstore.tenantyrfidservice.model.event;

import com.sportstore.tenantyrfidservice.event.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RFIDTagScannedEvent extends Event {

    private Long savedEventId;

}
