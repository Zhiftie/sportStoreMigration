package com.sportstore.tenantyeventservice.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQCreateConfig {

    @Resource(name = "v2RabbitAdmin")
    private RabbitAdmin v2RabbitAdmin;


    @PostConstruct
    public void init() {
    }
}
