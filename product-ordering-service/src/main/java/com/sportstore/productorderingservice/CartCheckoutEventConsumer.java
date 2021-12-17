package com.sportstore.productorderingservice;

import static com.sportstore.productorderingservice.config.RabbitMQConfig.ORDER_CREATED_EXCHANGE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.sportstore.productorderingservice.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.sportstore.productorderingservice.eventbus.EventBusService;
import com.sportstore.productorderingservice.model.db.Order;
import com.sportstore.productorderingservice.model.db.OrderLine;
import com.sportstore.productorderingservice.model.db.ShippingInfo;
import com.sportstore.productorderingservice.model.dto.CartDTO;
import com.sportstore.productorderingservice.model.dto.OrderLineDTO;
import com.sportstore.productorderingservice.model.dto.OrdersDTO;
import com.sportstore.productorderingservice.model.dto.ShippingInfoDTO;
import com.sportstore.productorderingservice.model.events.CartCheckoutEvent;
import com.sportstore.productorderingservice.model.events.OrderCreatedEvent;

import org.springframework.amqp.support.AmqpHeaders;

import org.springframework.messaging.handler.annotation.Header;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CartCheckoutEventConsumer {

    private final EventBusService eventBusService;
    private final OrderRepository orderRepository;
    private final ShippingInfoRepository shippingInfoRepository;
    private final OrderLineRepository orderLineRepository;

    @RabbitListener(queues = RabbitMQConfig.CART_CHECKOUT_EVENT, containerFactory = "v2ContainerFactory")
    public void onMessageReceivedTenantY(CartCheckoutEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        handleOrderCreate(message.getCartDTO(), "TenantY");
    }

    @RabbitListener(queues = RabbitMQConfig.CART_CHECKOUT_EVENT, containerFactory = "v1ContainerFactory")
    public void onMessageReceivedTenantX(CartCheckoutEvent message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        handleOrderCreate(message.getCartDTO(), "TenantX");
    }

    private void handleOrderCreate(CartDTO cartDTO, String tenant) {
        Order order = new Order();
        order.setCustomerId(cartDTO.getName());
        order.setTotalCost(cartDTO.getTotalCost());
        orderRepository.save(order);

        ShippingInfo shippingInfo = new ShippingInfo();
        shippingInfo.setCity(cartDTO.getCity());
        shippingInfo.setCountry(cartDTO.getCountry());
        shippingInfo.setGiftWrap(cartDTO.isGiftWrap());
        shippingInfo.setShipped(false);
        shippingInfo.setLine1(cartDTO.getLine1());
        shippingInfo.setLine2(cartDTO.getLine2());
        shippingInfo.setLine3(cartDTO.getLine3());
        shippingInfo.setState(cartDTO.getState());
        shippingInfo.setName(cartDTO.getName());
        shippingInfo.setZip(cartDTO.getZip());
        shippingInfo.setOrderId(order.getOrderId());
        shippingInfoRepository.save(shippingInfo);
        order.setShippingInfo(shippingInfo);
        List<OrderLine> orderLines = new ArrayList<>();
        cartDTO.getLines().forEach(cl -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setQuantity((long) cl.getQuantity());
            orderLine.setProductId((long) cl.getProduct().getProductID());
            orderLine.setOrderId(order.getOrderId());
            orderLine.setProductName(cl.getProduct().getName());
            orderLines.add(orderLine);
        });
        orderLineRepository.saveAll(orderLines);
        order.setOrderLines(orderLines);
        publishOrderCreated(order, tenant);
        //TODO create order from cartDTO and save and then publish new event
    }

    private void publishOrderCreated(Order order, String tenant) {
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrderId(order.getOrderId());
        ordersDTO.setCustomerId(order.getCustomerId());
        ordersDTO.setTotalCost(order.getTotalCost());
        List<OrderLineDTO> orderLineDTOS = new ArrayList<>();
        order.getOrderLines().forEach(o -> {
            OrderLineDTO orderLineDTO = new OrderLineDTO();
            orderLineDTO.setOrderLineId(o.getOrderLineId());
            orderLineDTO.setOrderId(o.getOrderId());
            orderLineDTO.setQuantity(o.getQuantity());
            orderLineDTO.setProductId(o.getProductId());
            orderLineDTOS.add(orderLineDTO);
        });
        ordersDTO.setLines(orderLineDTOS);
        ShippingInfoDTO shippingInfoDTO = new ShippingInfoDTO();
        shippingInfoDTO.setShippingInfoId(order.getShippingInfo().getShippingInfoId());
        shippingInfoDTO.setCity(order.getShippingInfo().getCity());
        shippingInfoDTO.setCountry(order.getShippingInfo().getCountry());
        shippingInfoDTO.setShipped(order.getShippingInfo().getShipped());
        shippingInfoDTO.setGiftWrap(order.getShippingInfo().getGiftWrap());
        shippingInfoDTO.setLine1(order.getShippingInfo().getLine1());
        shippingInfoDTO.setLine2(order.getShippingInfo().getLine2());
        shippingInfoDTO.setLine3(order.getShippingInfo().getLine3());
        shippingInfoDTO.setName(order.getShippingInfo().getName());
        shippingInfoDTO.setState(order.getShippingInfo().getState());
        shippingInfoDTO.setOrderId(order.getShippingInfo().getOrderId());
        ordersDTO.setShippingInfo(shippingInfoDTO);
        // TODO replace with EventBusServiceImpl
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setOrdersDTO(ordersDTO);
        orderCreatedEvent.setTenant(tenant);
        orderCreatedEvent.setName(OrderCreatedEvent.class.getSimpleName());

        eventBusService.publishEvent(ORDER_CREATED_EXCHANGE, orderCreatedEvent);
    }
}
