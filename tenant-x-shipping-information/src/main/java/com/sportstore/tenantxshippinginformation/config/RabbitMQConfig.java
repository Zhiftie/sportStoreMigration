package com.sportstore.tenantxshippinginformation.config;

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

    public static final String ORDER_CREATED = "tenant-x-shipping-information-service-ORDER_CREATED";
    public static final String ORDER_CREATED_EXCHANGE = "sportstore.order.created";

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean(name = "v1ConnectionFactory")
    @Primary
    public CachingConnectionFactory tenantXConnectionFactory(
            @Value("${v1.spring.rabbitmq.host}") String host,
            @Value("${v1.spring.rabbitmq.port}") int port,
            @Value("${v1.spring.rabbitmq.username}") String username,
            @Value("${v1.spring.rabbitmq.password}") String password,
            @Value("${v1.spring.rabbitmq.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean(name = "v1RabbitTemplate")
    @Primary
    public RabbitTemplate tenantXRabbitTemplate(
            @Qualifier("v1ConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter());
        return rabbitTemplate;
    }

    @Bean(name = "v1ContainerFactory")
    @Primary
    public SimpleRabbitListenerContainerFactory tenantXListenerFactory(
            @Qualifier("v1ConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonConverter());
        return factory;
    }

    @Bean(name = "v1RabbitAdmin")
    @Primary
    public RabbitAdmin tenantXRabbitAdmin(
            @Qualifier("v1ConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
