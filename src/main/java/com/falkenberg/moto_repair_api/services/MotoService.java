package com.falkenberg.moto_repair_api.services;

import com.falkenberg.moto_repair_api.components.UtilComponent;
import com.falkenberg.moto_repair_api.dtos.MotoDto;
import com.falkenberg.moto_repair_api.entities.Customer;
import com.falkenberg.moto_repair_api.repositories.CustomerRepository;
import com.falkenberg.moto_repair_api.repositories.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    private final CustomerRepository customerRepository;

    private final UtilComponent utilComponent;

    public MotoService(MotoRepository motoRepository, UtilComponent utilComponent, CustomerRepository customerRepository){
        this.motoRepository=motoRepository;
        this.utilComponent=utilComponent;
        this.customerRepository=customerRepository;
    }

    public MotoDto addMoto(MotoDto motoDtoCycle) {

        Customer customer = customerRepository.findById(motoDtoCycle.customer_id())
                .orElseThrow(() -> new EntityNotFoundException("CustomerDto not found"));

        com.falkenberg.moto_repair_api.entities.Moto moto = utilComponent.motoCycleDtoToEntity(motoDtoCycle);
        moto.setCustomer(customer);

        return utilComponent.motoCycleEntityToDto(motoRepository.save(moto));
    }

    public List<MotoDto> getAllMotos(){
        return motoRepository.findAll().stream().map(utilComponent::motoCycleEntityToDto).toList();
    }

}
