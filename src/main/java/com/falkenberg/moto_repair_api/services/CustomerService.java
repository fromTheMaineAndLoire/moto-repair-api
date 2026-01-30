package com.falkenberg.moto_repair_api.services;

import com.falkenberg.moto_repair_api.components.UtilComponent;
import com.falkenberg.moto_repair_api.dtos.Customer;
import com.falkenberg.moto_repair_api.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final UtilComponent utilComponent;

    public CustomerService(CustomerRepository customerRepository, UtilComponent utilComponent){
        this.customerRepository=customerRepository;
        this.utilComponent=utilComponent;
    }

    public Customer addCustomer(Customer customer){
        return utilComponent.customerEntityToDto(customerRepository.save(utilComponent.customerDtoToEntity(customer)));
    }

    public Customer getCustomer(long id){

        return utilComponent.customerEntityToDto(customerRepository.getReferenceById(id));
    }


}
