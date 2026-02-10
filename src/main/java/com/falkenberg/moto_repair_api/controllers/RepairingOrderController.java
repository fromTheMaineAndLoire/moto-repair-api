package com.falkenberg.moto_repair_api.controllers;

import com.falkenberg.moto_repair_api.dtos.RepairingOrderDto;
import com.falkenberg.moto_repair_api.enums.Status;
import com.falkenberg.moto_repair_api.services.RepairingOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/bons")
@Tag(name = "Bon de réparation", description = "Permet la gestion des bons de réparation")
public class RepairingOrderController {

    Logger logger = LoggerFactory.getLogger(RepairingOrderController.class);

    private final RepairingOrderService repairingOrderService;

    public RepairingOrderController(RepairingOrderService repairingOrderService){
        this.repairingOrderService=repairingOrderService;
    }

    // Get bon de réparation - ajouter pagination et filtre sur status, mécanicien, client et priorité
    @GetMapping(value = "/")
    @Operation(description = "Récupérer les ordres de réparation")
    public ResponseEntity<Page<RepairingOrderDto>> getRepairingOrderList(@RequestParam(defaultValue = "0")  int page,
                                                                         @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.status(HttpStatus.FOUND).body(repairingOrderService.getRepairingOrderList(page,size));
    }

    // GET    /bons/{id}
    @GetMapping(value= "/{id}")
    @Operation(description = "Récupérer un ordre de réparation par son id")
    public ResponseEntity<RepairingOrderDto> getRepairingOrder(@PathVariable Long id){
        logger.info("Id {}",id);
        return ResponseEntity.status(HttpStatus.FOUND).body(repairingOrderService.getRepairingOrder(id));
    }

    // POST   /bons
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Créer un ordre de réparation")
    public ResponseEntity<RepairingOrderDto> addRepairingOrder(@Valid RepairingOrderDto repairingOrderDto){
        logger.info("Repairing order {}", repairingOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(repairingOrderService.addRepairingOrder(repairingOrderDto));
    }

    // PUT    /bons/{id}
    @PutMapping(value = "{id}")
    @Operation(description = "Mettre à jour les information d'un ordre de réparation")
    public ResponseEntity<RepairingOrderDto> updateRepairingOrder(@PathVariable Long id, @Valid @RequestBody RepairingOrderDto repairingOrderDto){
        logger.info("Id {}",id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repairingOrderService.putRepairingOrder(id, repairingOrderDto));
    }

    // PATCH  /bons/{id}/statut
    @PatchMapping(value = "/{id}/statut")
    @Operation(description = "Mettre à jour le status d'un ordre de réparation")
    public HttpStatus updateStatus(@PathVariable Long id, @RequestBody Status status){
        logger.info("Id {}",id);

        return (repairingOrderService.patchRepairingOrder(id,status)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;

    }

    //DELETE /bons/{id}
    @DeleteMapping(value = "/{id}")
    @Operation(description = "Supprimer un ordre de réparation")
    public HttpStatus deleteRepairingOrder(@PathVariable Long id){
        logger.info("Id {}",id);
        repairingOrderService.deleteRepairingOrder(id);
        return HttpStatus.ACCEPTED;
    }
}
