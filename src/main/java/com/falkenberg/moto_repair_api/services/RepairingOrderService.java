package com.falkenberg.moto_repair_api.services;

import com.falkenberg.moto_repair_api.components.UtilComponent;
import com.falkenberg.moto_repair_api.dtos.RepairingOrder;
import com.falkenberg.moto_repair_api.enums.Status;
import com.falkenberg.moto_repair_api.repositories.RepairingOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RepairingOrderService {

    private final RepairingOrderRepository repairingOrderRepository;

    private final UtilComponent utilComponent;

    public RepairingOrderService(RepairingOrderRepository repairingOrderRepository, UtilComponent utilComponent){
        this.repairingOrderRepository=repairingOrderRepository;
        this.utilComponent=utilComponent;
    }

    public Page<RepairingOrder> getRepairingOrderList(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return repairingOrderRepository.findAll(pageable)
                .map(utilComponent::repairingOrderEntityToDto);

    }

    public RepairingOrder getRepairingOrder(Long id){
        return utilComponent.repairingOrderEntityToDto(repairingOrderRepository.getReferenceById(id));
    }

    public RepairingOrder addRepairingOrder(RepairingOrder repairingOrder){
        return utilComponent.repairingOrderEntityToDto(repairingOrderRepository.save(utilComponent.repairingOrderToEntity(repairingOrder)));
    }

    public RepairingOrder putRepairingOrder(Long id, RepairingOrder repairingOrder){
        return utilComponent.repairingOrderEntityToDto(repairingOrderRepository.save(utilComponent.repairingOrderToEntity(repairingOrder)));
    }

    public int patchRepairingOrder(Long id, Status status){
         return repairingOrderRepository.updateStatusById(id,status);
    }

    public void deleteRepairingOrder(Long id) {
        repairingOrderRepository.delete(repairingOrderRepository.getReferenceById(id));
    }

}
