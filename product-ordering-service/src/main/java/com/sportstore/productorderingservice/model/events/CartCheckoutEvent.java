package com.sportstore.productorderingservice.model.events;

import com.sportstore.productorderingservice.eventbus.Event;
import com.sportstore.productorderingservice.model.dto.CartDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CartCheckoutEvent extends Event {

    private CartDTO cartDTO;
}
