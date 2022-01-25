package com.sportstore.tenantxshippinginformation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sportstore.tenantxshippinginformation.model.db.ShippingInformation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("shippinginformation")
public class ShippingInformationResource {

    private final ShippingInformationRepository shippingInformationRepository;

    @GetMapping
    public List<ShippingInformation> getAllShippingInformation() {
        return StreamSupport.stream((shippingInformationRepository.findAll().spliterator()), false).collect(Collectors.toList());
    }

    @GetMapping("{orderId}")
    public ShippingInformation getShippingInformation(@PathVariable long orderId) {
        ShippingInformation shippingInformation = shippingInformationRepository.findShippingInformationByOrderId(orderId);

        if (shippingInformation != null) {
            return shippingInformation;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shipping information not found");
    }

}
