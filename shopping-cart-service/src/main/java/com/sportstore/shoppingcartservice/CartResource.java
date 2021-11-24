package com.sportstore.shoppingcartservice;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportstore.shoppingcartservice.config.RabbitMQConfig;
import com.sportstore.shoppingcartservice.order.OrdersDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("cart-api")
public class CartResource {

    private final CartLineRepository cartLineRepository;
    private final RabbitTemplate rabbitTemplate;


    @GetMapping("cart")
    public List<CartLine> getCart() {
        return StreamSupport.stream((cartLineRepository.findAll().spliterator()), false).collect(Collectors.toList());
    }

    @PostMapping("cart")
    public List<CartLine> addCartLine() {
        throw new UnsupportedOperationException("Todo");
    }

    @GetMapping("cartline")
    public CartLine getCartLine() {
        throw new UnsupportedOperationException("Todo");
    }

    @PutMapping("cartline")
    public CartLine updateCartLine() {
        throw new UnsupportedOperationException("Todo");
    }

    @PostMapping("checkout")
    public CartLine checkout() {
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrderId(1L);
        ordersDTO.setCustomerId("123");
        ordersDTO.setTotalCost(999d);
        //TODO fix
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_CREATE, ordersDTO);
        return new CartLine();
    }

}
