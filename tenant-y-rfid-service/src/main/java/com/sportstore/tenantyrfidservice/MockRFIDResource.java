package com.sportstore.tenantyrfidservice;

import static com.sportstore.tenantyrfidservice.config.RabbitMQConfig.TENANT_Y_RFID_EXCHANGE;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportstore.tenantyrfidservice.event.EventBusService;
import com.sportstore.tenantyrfidservice.model.db.RfidTag;
import com.sportstore.tenantyrfidservice.model.event.RFIDTagScannedEvent;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("rfid")
public class MockRFIDResource {

    private final RFIDRepository rfidRepository;
    private final EventBusService eventBusService;

    @PostMapping("{orderSavedEventId}")
    public void rfidTagsScanned(@PathVariable long orderSavedEventId){
        RfidTag rfidTag = rfidRepository.findBySavedEventId(orderSavedEventId);
        RFIDTagScannedEvent rfidTagScannedEvent = new RFIDTagScannedEvent();
        rfidTagScannedEvent.setSavedEventId(rfidTag.getSavedEventId());
        rfidTagScannedEvent.setName(RFIDTagScannedEvent.class.getSimpleName());
        rfidTagScannedEvent.setTenant("TenantY");
        eventBusService.publishEvent(TENANT_Y_RFID_EXCHANGE, rfidTagScannedEvent);
    }

}
