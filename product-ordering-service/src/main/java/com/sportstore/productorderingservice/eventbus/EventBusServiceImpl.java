package com.sportstore.productorderingservice.eventbus;

import static com.sportstore.productorderingservice.config.RabbitMQConfig.ORDER_CREATED_EXCHANGE;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventBusServiceImpl implements EventBusService {

    @Resource
    private final Map<String, RabbitTemplate> rabbitTemplateMap;
    //private final TenantMangerServiceImpl

    @Override
    public void publishEvent(String exchange, Event event) {
        // TODO check if event is customised in tenant manager
        // IF customised send event to endpoint specified in tenant manager response and return
        // TenantX == 1 and TenantY == 2
        // Probably easier to make new TenantManager in Java
        // CustomisationResource host:port/customisation?tenant=TENANT_NAME&event=EVENT_NAME
        // Returns true/false, including endpoint if true

        // TODO call endpoint
        // IF not customised publish event as normal
        rabbitTemplateMap.get(event.getTenant()).convertAndSend(exchange, "", event);
    }
}
