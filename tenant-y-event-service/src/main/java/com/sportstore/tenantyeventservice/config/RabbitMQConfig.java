package com.sportstore.tenantyeventservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig {

    public static final String SHIPPING_EXCHANGE = "sportstore.shipping";
    public static final String CUSTOMISATION_EXCHANGE = "sportstore.customisation";
    public static final String TENANT_Y_EVENT_EXCHANGE = "sportstore.tenanty.event";
    public static final String TENANT_Y_RFID_EXCHANGE = "sportstore.tenanty.rfid";
    public static final String RFID_TAG_SCANNED_EVENT = "tenant-y-event-service-RFID_TAG_SCANNED_EVENT";
    public static final String CUSTOMISATION_EVENT = "tenant-y-event-service-CUSTOMISATION_EVENT";


    @Bean(name = "v2ConnectionFactory")
    public CachingConnectionFactory tenantYConnectionFactory(
            @Value("${v2.spring.rabbitmq.host}") String host,
            @Value("${v2.spring.rabbitmq.port}") int port,
            @Value("${v2.spring.rabbitmq.username}") String username,
            @Value("${v2.spring.rabbitmq.password}") String password,
            @Value("${v2.spring.rabbitmq.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean(name = "v2RabbitTemplate")
    @Primary
    public RabbitTemplate tenantYRabbitTemplate(
            @Qualifier("v2ConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = "v2ContainerFactory")
    public SimpleRabbitListenerContainerFactory tenantYListenerFactory(
            @Qualifier("v2ConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonConverter());
        return factory;
    }

    @Bean(name = "v2RabbitAdmin")
    public RabbitAdmin tenantYRabbitAdmin(
            @Qualifier("v2ConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }


    @Bean
    public Map<String, RabbitTemplate> rabbitTemplateMap(@Qualifier("v2ConnectionFactory") ConnectionFactory connectionFactoryTenantY) {
        HashMap<String, RabbitTemplate> rabbitTemplateHashMap = new HashMap<>();
        rabbitTemplateHashMap.put("TenantY", tenantYRabbitTemplate(connectionFactoryTenantY));

        return rabbitTemplateHashMap;
    }
}
