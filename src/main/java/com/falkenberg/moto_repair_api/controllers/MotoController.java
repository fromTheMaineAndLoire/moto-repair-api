package com.falkenberg.moto_repair_api.controllers;

import com.falkenberg.moto_repair_api.dtos.MotoDto;
import com.falkenberg.moto_repair_api.services.MotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/motos")
@Tag(name = "Motos", description = "Gestion des motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @Operation(description = "Créer les informations d'une nouvelle motoDto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MotoDto addMoto(@Valid @RequestBody MotoDto motoDto){
        return motoService.addMoto(motoDto);
    }

    @Operation(description = "Récupérer la liste des motos")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MotoDto> getAllMotos(){
        return motoService.getAllMotos();
    }

}
