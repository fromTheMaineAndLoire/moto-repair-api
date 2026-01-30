package com.falkenberg.moto_repair_api.services;

import com.falkenberg.moto_repair_api.components.UtilComponent;
import com.falkenberg.moto_repair_api.dtos.MotoCycle;
import com.falkenberg.moto_repair_api.repositories.MotoRepository;
import org.springframework.stereotype.Service;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    private final UtilComponent utilComponent;

    public MotoService(MotoRepository motoRepository, UtilComponent utilComponent){
        this.motoRepository=motoRepository;
        this.utilComponent=utilComponent;
    }

    public MotoCycle addMoto(MotoCycle motoCycle) {
        return utilComponent.motoCycleEntityToDto(motoRepository.save(utilComponent.motoCycleDtoToEntity(motoCycle)));
    }


}
