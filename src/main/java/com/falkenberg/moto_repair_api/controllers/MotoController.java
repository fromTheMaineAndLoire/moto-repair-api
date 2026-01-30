package com.falkenberg.moto_repair_api.controllers;

import com.falkenberg.moto_repair_api.dtos.MotoCycle;
import com.falkenberg.moto_repair_api.services.MotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/motos")
@Tag(name = "Motos", description = "Gestion des motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @Operation(description = "Créer les informations d'une nouvelle moto")
    @PostMapping
    public ResponseEntity<MotoCycle> addMoto(@Valid @RequestBody MotoCycle motoCycle){
        MotoCycle motoCycle1=motoService.addMoto(motoCycle);
        return ResponseEntity.status(HttpStatus.CREATED).body(motoCycle1);
    }

}
