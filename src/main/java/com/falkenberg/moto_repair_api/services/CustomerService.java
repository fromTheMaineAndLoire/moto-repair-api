package com.falkenberg.moto_repair_api.services;

import com.falkenberg.moto_repair_api.components.UtilComponent;
import com.falkenberg.moto_repair_api.dtos.CustomerDto;
import com.falkenberg.moto_repair_api.entities.Customer;
import com.falkenberg.moto_repair_api.exceptions.ErrorCode;
import com.falkenberg.moto_repair_api.exceptions.ApiException;
import com.falkenberg.moto_repair_api.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final UtilComponent utilComponent;

    public CustomerService(CustomerRepository customerRepository, UtilComponent utilComponent){
        this.customerRepository=customerRepository;
        this.utilComponent=utilComponent;
    }

    public CustomerDto addCustomer(CustomerDto customerDto){
        Customer customer = utilComponent.customerDtoToEntity(customerDto);

        if(customerRepository.existsById(customer.getId())){
            throw new ApiException("Customer already exists",ErrorCode.ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        Customer savedCustomer = customerRepository.save(customer);

        return utilComponent.customerEntityToDto(savedCustomer);
    }

    public CustomerDto getCustomer(long id){
        return  customerRepository.findById(id)
                .map(utilComponent::customerEntityToDto)
                .orElseThrow(() -> new ApiException("Customer not found",ErrorCode.NOT_FOUND,HttpStatus.NOT_FOUND));

   }


}
