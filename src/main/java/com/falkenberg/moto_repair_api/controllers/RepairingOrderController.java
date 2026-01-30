package com.falkenberg.moto_repair_api.controllers;

import com.falkenberg.moto_repair_api.dtos.RepairingOrder;
import com.falkenberg.moto_repair_api.enums.Status;
import com.falkenberg.moto_repair_api.services.RepairingOrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bons")
public class RepairingOrderController {

    Logger logger = LoggerFactory.getLogger(RepairingOrderController.class);

    private final RepairingOrderService repairingOrderService;

    public RepairingOrderController(RepairingOrderService repairingOrderService){
        this.repairingOrderService=repairingOrderService;
    }

    // Get bon de réparation - ajouter pagination et filtre sur status, mécanicien, client et priorité
    @GetMapping(value = "/")
    public ResponseEntity<List<RepairingOrder>> getRepairingOrderList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(repairingOrderService.getRepairingOrderList());
    }

    // GET    /bons/{id}
    @GetMapping(value= "/{id}")
    public ResponseEntity<RepairingOrder> getRepairingOrder(@PathVariable Long id){
        logger.info("Id {}",id);
        return ResponseEntity.status(HttpStatus.FOUND).body(repairingOrderService.getRepairingOrder(id));
    }

    // POST   /bons
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepairingOrder> addRepairingOrder(@Valid RepairingOrder repairingOrder){
        logger.info("Repairing order {}", repairingOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(repairingOrderService.addRepairingOrder(repairingOrder));
    }

    // PUT    /bons/{id}
    @PutMapping(value = "{id}")
    public ResponseEntity<RepairingOrder> updateRepairingOrder(@PathVariable Long id, @Valid @RequestBody RepairingOrder repairingOrder){
        logger.info("Id {}",id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repairingOrderService.putRepairingOrder(id,repairingOrder));
    }

    // PATCH  /bons/{id}/statut
    @PatchMapping(value = "/{id}/statut")
    public HttpStatus updateStatus(@PathVariable Long id, @RequestBody Status status){
        logger.info("Id {}",id);

        return (repairingOrderService.patchRepairingOrder(id,status)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;

    }

    //DELETE /bons/{id}
    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteRepairingOrder(@PathVariable Long id){
        logger.info("Id {}",id);
        repairingOrderService.deleteRepairingOrder(id);
        return HttpStatus.ACCEPTED;
    }
}
