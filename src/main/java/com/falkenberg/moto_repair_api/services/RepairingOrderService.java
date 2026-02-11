package com.falkenberg.moto_repair_api.services;

import com.falkenberg.moto_repair_api.components.UtilComponent;
import com.falkenberg.moto_repair_api.dtos.RepairingOrderDto;
import com.falkenberg.moto_repair_api.entities.RepairingOrder;
import com.falkenberg.moto_repair_api.enums.Status;
import com.falkenberg.moto_repair_api.exceptions.ApiException;
import com.falkenberg.moto_repair_api.exceptions.ErrorCode;
import com.falkenberg.moto_repair_api.repositories.RepairingOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RepairingOrderService {

    private final RepairingOrderRepository repairingOrderRepository;

    private final UtilComponent utilComponent;

    public RepairingOrderService(RepairingOrderRepository repairingOrderRepository, UtilComponent utilComponent){
        this.repairingOrderRepository=repairingOrderRepository;
        this.utilComponent=utilComponent;
    }

    public Page<RepairingOrderDto> getRepairingOrderList(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return repairingOrderRepository.findAll(pageable)
                .map(utilComponent::repairingOrderEntityToDto);

    }

    public RepairingOrderDto getRepairingOrder(Long id){

        return utilComponent.repairingOrderEntityToDto(repairingOrderRepository.getReferenceById(id));
    }

    public RepairingOrderDto addRepairingOrder(RepairingOrderDto repairingOrderDto){

        RepairingOrder repairingOrder = utilComponent.repairingOrderToEntity(repairingOrderDto);

        RepairingOrder savedRepairingOrderDto = repairingOrderRepository.save(repairingOrder);

        return utilComponent.repairingOrderEntityToDto(savedRepairingOrderDto);
    }

    public RepairingOrderDto putRepairingOrder(Long id, RepairingOrderDto repairingOrderDto){
        return utilComponent.repairingOrderEntityToDto(repairingOrderRepository.save(utilComponent.repairingOrderToEntity(repairingOrderDto)));
    }

    public int patchRepairingOrder(Long id, Status status){
         return repairingOrderRepository.updateStatusById(id,status);
    }

    public void deleteRepairingOrder(Long id) {

        if(!repairingOrderRepository.existsById(id)){
            throw new ApiException("", ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        repairingOrderRepository.delete(repairingOrderRepository.getReferenceById(id));
    }

}
