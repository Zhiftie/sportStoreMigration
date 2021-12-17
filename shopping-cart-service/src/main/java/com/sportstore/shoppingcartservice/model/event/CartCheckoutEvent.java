package com.sportstore.shoppingcartservice.model.event;

import com.sportstore.shoppingcartservice.eventbus.Event;
import com.sportstore.shoppingcartservice.model.dto.CartDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CartCheckoutEvent extends Event {

    private CartDTO cartDTO;
}
