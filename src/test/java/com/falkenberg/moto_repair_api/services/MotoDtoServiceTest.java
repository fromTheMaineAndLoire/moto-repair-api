package com.falkenberg.moto_repair_api.services;

import com.falkenberg.moto_repair_api.components.UtilComponent;
import com.falkenberg.moto_repair_api.dtos.CustomerDto;
import com.falkenberg.moto_repair_api.dtos.MotoDto;
import com.falkenberg.moto_repair_api.entities.Customer;
import com.falkenberg.moto_repair_api.repositories.CustomerRepository;
import com.falkenberg.moto_repair_api.repositories.MotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MotoDtoServiceTest {

    @InjectMocks
    MotoService motoServiceTest;

    @Mock
    MotoRepository motoRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    UtilComponent utilComponent;



    @Test
    void should_success_when_add_motocycle(){

        // Given
        CustomerDto customerDto =new CustomerDto(1L,
                "Me",
                "+33123456789",
                "me@git.com");

        Customer customerEntity = new Customer();

        MotoDto motoDto = new MotoDto(1L,
                "Honda",
                "Hornet 750",
                2023,
                "AB-123-AB",
                customerDto.id());

        com.falkenberg.moto_repair_api.entities.Moto motoEntity = new com.falkenberg.moto_repair_api.entities.Moto();
        motoEntity.setId(1L);

        com.falkenberg.moto_repair_api.entities.Moto savedMotoEntity = new com.falkenberg.moto_repair_api.entities.Moto();
        savedMotoEntity.setId(1L);

        MotoDto savedMotoDto = new MotoDto(1L,
                "Honda",
                "Hornet 750",
                2023,
                "AB-123-AB",
                customerDto.id());

        // When
        Mockito.when(utilComponent.motoCycleDtoToEntity(motoDto)).thenReturn(motoEntity);

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customerEntity));

        motoEntity.setCustomer(customerEntity);
        Mockito.when(motoRepository.save(motoEntity)).thenReturn(savedMotoEntity);
        Mockito.when(utilComponent.motoCycleEntityToDto(savedMotoEntity)).thenReturn(savedMotoDto);

        savedMotoDto = motoServiceTest.addMoto(motoDto);

        // Then
       Assertions.assertEquals(motoDto.id(), savedMotoDto.id());
       Assertions.assertEquals(motoDto.model(), savedMotoDto.model());
       Assertions.assertEquals(motoDto.marque(), savedMotoDto.marque());
       Assertions.assertEquals(motoDto.year(), savedMotoDto.year());
       Assertions.assertEquals(motoDto.immatriculation(), savedMotoDto.immatriculation());

       Mockito.verify(motoRepository).save(motoEntity);
    }


}
