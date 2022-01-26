package com.sportstore.productshippingservice.eventbus;

import static com.sportstore.productshippingservice.config.RabbitMQConfig.CUSTOMISATION_EXCHANGE;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportstore.productshippingservice.model.event.CustomisationEvent;
import com.sportstore.shoppingcartservice.model.dto.CustomisationInfoDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventBusServiceImpl implements EventBusService {

    @Value("${use.rabbitmq}")
    private boolean useRabbitMQ;

    @Resource
    private final Map<String, RabbitTemplate> rabbitTemplateMap;

    @Override
    public void publishEvent(String exchange, Event event) {
        CustomisationInfoDTO customisationInfoDTO = checkIfEventIsCustomised(event);
        if(!event.isDoNotCheckForCustomisation() && customisationInfoDTO.getEndpoint() != null) {
            sendEventToTenant(customisationInfoDTO, event);
        } else {
            rabbitTemplateMap.get(event.getTenant()).convertAndSend(exchange, "", event);
        }
    }

    private CustomisationInfoDTO checkIfEventIsCustomised(Event event) {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        CustomisationInfoDTO customisationInfoDTO = new CustomisationInfoDTO();
        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    "http://localhost:8084/customisations?tenant=" + event.getTenant() + "&event=" + event.getName(), HttpMethod.GET, entity,
                    Map.class);
            customisationInfoDTO.setEndpoint(Objects.requireNonNull(response.getBody()).get("endpoint").toString());
        } catch (HttpClientErrorException e) {
            //Fail silently
        }

        return customisationInfoDTO;
    }

    private void sendEventToTenant(CustomisationInfoDTO customisationInfoDTO, Event event) {
        if(useRabbitMQ) {
            sendEventToTenantWithRabbitMQ(event);
        } else {
            sendEventToTenantWithREST(customisationInfoDTO, event);
        }
    }

    private void sendEventToTenantWithREST(CustomisationInfoDTO customisationInfoDTO, Event event){
        RestTemplate restTemplate = new RestTemplate();
        //Execute the method writing your HttpEntity to the request
        try {
            restTemplate.postForEntity(customisationInfoDTO.getEndpoint(), event, Void.class);
        } catch (HttpClientErrorException e) {
            //Fail silently
            System.out.println("Something went wrong when sending event to tenant endpoint");
            System.out.println(e);
        }
    }

    private void sendEventToTenantWithRabbitMQ(Event event){
        CustomisationEvent customisationEvent = new CustomisationEvent();
        customisationEvent.setTenant(event.getTenant());
        customisationEvent.setName(event.getName());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        customisationEvent.setJson(json);
        rabbitTemplateMap.get(event.getTenant()).convertAndSend(CUSTOMISATION_EXCHANGE, "", customisationEvent);
    }
}
