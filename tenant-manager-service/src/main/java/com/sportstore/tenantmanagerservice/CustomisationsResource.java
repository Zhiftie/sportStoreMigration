package com.sportstore.tenantmanagerservice;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("customisations")
public class CustomisationsResource {

    private final CustomisationsRepository customisationsRepository;

    @GetMapping
    public Customisations getCustomisation(@RequestParam String tenant, @RequestParam String event) {
        if (event == null || tenant == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tenant and event must be specified");
        }
        Optional<Customisations> optionalCustomisations = customisationsRepository.findFirstByTenantNameAndEventNameEquals(tenant, event);
        if (optionalCustomisations.isPresent()) {
            return optionalCustomisations.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customisation not found");
    }


}
