package com.falkenberg.moto_repair_api.controllers;

import com.falkenberg.moto_repair_api.dtos.CustomerDto;
import com.falkenberg.moto_repair_api.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/clients")
@Tag(name = "Client",description = "Gestion des clients")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Créer un client")
    public CustomerDto addCustomer(@Valid @RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Récupérer les informations d'un client")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long id){
        return customerService.getCustomer(id);
    }

}
