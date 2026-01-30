package com.falkenberg.moto_repair_api.controllers;

import com.falkenberg.moto_repair_api.dtos.Customer;
import com.falkenberg.moto_repair_api.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/clients")
@Tag(name = "Client",description = "Gestion des clients")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping
    @Operation(description = "Créer un client")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer){
        Customer customer1 = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer1);
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Récupérer les informations d'un client")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(customer);
    }
}
