package com.sportstore.tenantyeventservice.config;

import static com.sportstore.tenantyeventservice.config.RabbitMQConfig.RFID_TAG_SCANNED_EVENT;
import static com.sportstore.tenantyeventservice.config.RabbitMQConfig.TENANT_Y_EVENT_EXCHANGE;
import static com.sportstore.tenantyeventservice.config.RabbitMQConfig.TENANT_Y_RFID_EXCHANGE;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQCreateConfig {

    @Resource(name = "v2RabbitAdmin")
    private RabbitAdmin v2RabbitAdmin;

    @PostConstruct
    public void init() {
        v2RabbitAdmin.declareExchange(new FanoutExchange(TENANT_Y_EVENT_EXCHANGE, true, false));

        v2RabbitAdmin.declareQueue(new Queue(RFID_TAG_SCANNED_EVENT, false));
        v2RabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue(RFID_TAG_SCANNED_EVENT, false))
                        .to(new FanoutExchange(TENANT_Y_RFID_EXCHANGE, true, true)));
    }
}
