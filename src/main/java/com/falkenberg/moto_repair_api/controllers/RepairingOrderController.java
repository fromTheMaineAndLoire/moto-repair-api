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

    @GetMapping(value = "/")
    @Operation(description = "Récupérer les ordres de réparation")
    @ResponseStatus(HttpStatus.OK)
    public Page<RepairingOrderDto> getRepairingOrderList(@RequestParam(defaultValue = "0")  int page,
                                                                         @RequestParam(defaultValue = "10") int size){
        return repairingOrderService.getRepairingOrderList(page,size);
    }

    @GetMapping(value= "/{id}")
    @Operation(description = "Récupérer un ordre de réparation par son id")
    @ResponseStatus(HttpStatus.OK)
    public RepairingOrderDto getRepairingOrder(@PathVariable Long id){
        return repairingOrderService.getRepairingOrder(id);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Créer un ordre de réparation")
    public RepairingOrderDto addRepairingOrder(@Valid RepairingOrderDto repairingOrderDto){
        logger.info("Repairing order {}", repairingOrderDto);
        return repairingOrderService.addRepairingOrder(repairingOrderDto);
    }


    @PutMapping(value = "{id}")
    @Operation(description = "Mettre à jour les information d'un ordre de réparation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RepairingOrderDto updateRepairingOrder(@PathVariable Long id, @Valid @RequestBody RepairingOrderDto repairingOrderDto){
        logger.info("Id {}",id);
        return repairingOrderService.putRepairingOrder(id, repairingOrderDto);
    }


    @PatchMapping(value = "/{id}/statut")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(description = "Mettre à jour le status d'un ordre de réparation")
    public HttpStatus updateStatus(@PathVariable Long id, @RequestBody Status status){
        logger.info("Id {}",id);

        return (repairingOrderService.patchRepairingOrder(id,status)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;

    }


    @DeleteMapping(value = "/{id}")
    @Operation(description = "Supprimer un ordre de réparation")
    public HttpStatus deleteRepairingOrder(@PathVariable Long id){
        logger.info("Id {}",id);
        repairingOrderService.deleteRepairingOrder(id);
        return HttpStatus.ACCEPTED;
    }
}
