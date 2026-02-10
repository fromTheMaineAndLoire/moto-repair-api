package com.falkenberg.moto_repair_api.controllers;

import com.falkenberg.moto_repair_api.dtos.CustomerDto;
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
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto){
        CustomerDto customerDto1 = customerService.addCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto1);
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Récupérer les informations d'un client")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable(name = "id") Long id){
        logger.info("[ID] = {}",id);
        CustomerDto customerDto = customerService.getCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    /*@GetMapping
    @Operation(description = "Récupérer une liste de clients")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        CustomerDto customerDto = customerService.get
    }*/
}
