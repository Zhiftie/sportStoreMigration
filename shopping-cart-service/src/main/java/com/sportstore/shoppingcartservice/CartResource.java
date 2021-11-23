package com.sportstore.shoppingcartservice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("cart-api")
public class CartResource {

    private final CartLineRepository cartLineRepository;


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
        throw new UnsupportedOperationException("Todo");
    }

}
