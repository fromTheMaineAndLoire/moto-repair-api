package com.falkenberg.moto_repair_api.services;

import com.falkenberg.moto_repair_api.components.UtilComponent;
import com.falkenberg.moto_repair_api.dtos.Customer;
import com.falkenberg.moto_repair_api.dtos.MotoCycle;
import com.falkenberg.moto_repair_api.entities.MotoEntity;
import com.falkenberg.moto_repair_api.repositories.MotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MotoServiceTest {

    @InjectMocks
    MotoService motoServiceTest;

    @Mock
    MotoRepository motoRepository;

    @Mock
    UtilComponent utilComponent;



    @Test
    void should_success_when_add_motocycle(){

        // Given
        Customer customer =new  Customer(1L,
                "Me",
                "+33123456789",
                "me@git.com");

        MotoCycle motoCycle = new MotoCycle(1L,
                "Honda",
                "Hornet 750",
                2023,
                "AB-123-AB",
                customer);

        MotoEntity motoCycleEntity = new MotoEntity();
        motoCycleEntity.setId(1L);

        MotoEntity savedMotoCycleEntity = new MotoEntity();
        savedMotoCycleEntity.setId(1L);

        MotoCycle savedMotoCycle = new MotoCycle(1L,
                "Honda",
                "Hornet 750",
                2023,
                "AB-123-AB",
                customer);

        // When
        Mockito.when(utilComponent.motoCycleDtoToEntity(motoCycle)).thenReturn(motoCycleEntity);
        Mockito.when(motoRepository.save(motoCycleEntity)).thenReturn(savedMotoCycleEntity);
        Mockito.when(utilComponent.motoCycleEntityToDto(savedMotoCycleEntity)).thenReturn(savedMotoCycle);

        savedMotoCycle = motoServiceTest.addMoto(motoCycle);

        // Then
       Assertions.assertEquals(motoCycle.id(),savedMotoCycle.id());
       Assertions.assertEquals(motoCycle.model(),savedMotoCycle.model());
       Assertions.assertEquals(motoCycle.marque(),savedMotoCycle.marque());
       Assertions.assertEquals(motoCycle.year(),savedMotoCycle.year());
       Assertions.assertEquals(motoCycle.immatriculation(),savedMotoCycle.immatriculation());

       Mockito.verify(motoRepository).save(motoCycleEntity);
    }


}
