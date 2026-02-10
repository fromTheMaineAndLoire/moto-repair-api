package com.falkenberg.moto_repair_api.components;

import com.falkenberg.moto_repair_api.dtos.CustomerDto;
import com.falkenberg.moto_repair_api.dtos.MotoDto;
import com.falkenberg.moto_repair_api.dtos.RepairingOrderDto;
import com.falkenberg.moto_repair_api.dtos.UserDto;
import com.falkenberg.moto_repair_api.entities.Customer;
import com.falkenberg.moto_repair_api.entities.RepairingOrder;
import com.falkenberg.moto_repair_api.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UtilComponent {

    public Customer customerDtoToEntity(CustomerDto customerDto){
        Customer customerEntity = new Customer();
        customerEntity.setEmail(customerDto.email());
        customerEntity.setName(customerDto.name());
        customerEntity.setPhone(customerDto.phone());

        return customerEntity;
    }

    public CustomerDto customerEntityToDto(Customer customer){
        return new CustomerDto(customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail());
    }

    public MotoDto motoCycleEntityToDto(com.falkenberg.moto_repair_api.entities.Moto moto) {
        return new MotoDto(moto.getId(),
                moto.getMarque(),
                moto.getModele(),
                moto.getAnnee(),
                moto.getImmatriculation(),
                moto.getCustomer().getId());
    }

    public com.falkenberg.moto_repair_api.entities.Moto motoCycleDtoToEntity(MotoDto motoDtoCycle){
        com.falkenberg.moto_repair_api.entities.Moto moto = new com.falkenberg.moto_repair_api.entities.Moto();
        moto.setMarque(motoDtoCycle.marque());
        moto.setModele(motoDtoCycle.model());
        moto.setAnnee(motoDtoCycle.year());
        moto.setImmatriculation(motoDtoCycle.immatriculation());
        //motoDto.setClient(customerDtoToEntity(motoDto.id()));

        return moto;
    }

    public RepairingOrderDto repairingOrderEntityToDto(RepairingOrder repairingOrder){
        return new RepairingOrderDto(
                repairingOrder.getId(),
                repairingOrder.getReference(),
                repairingOrder.getProblemDescription(),
                repairingOrder.getStatus(),
                repairingOrder.getPriority(),
                repairingOrder.getCreatedAt(),
                repairingOrder.getUpdatedAt(),
                customerEntityToDto(repairingOrder.getCustomer()),
                motoCycleEntityToDto(repairingOrder.getMoto()),
                userToDto(repairingOrder.getUser())
        );
    }

    public RepairingOrder repairingOrderToEntity(RepairingOrderDto repairingOrderDto){
        RepairingOrder repairingOrderEntity =  new RepairingOrder();

        repairingOrderEntity.setReference(repairingOrderDto.reference());
        repairingOrderEntity.setStatus(repairingOrderDto.status());
        repairingOrderEntity.setPriority(repairingOrderDto.priority());
        repairingOrderEntity.setCreatedAt(repairingOrderDto.createdAt());
        repairingOrderEntity.setUpdatedAt(repairingOrderDto.updatedAt());
        repairingOrderEntity.setCustomer(customerDtoToEntity(repairingOrderDto.customerDto()));
        repairingOrderEntity.setMoto(motoCycleDtoToEntity(repairingOrderDto.motoDto()));
        repairingOrderEntity.setUser(userDtoToEntity(repairingOrderDto.mecanician()));

        return repairingOrderEntity;
    }


    public UserDto userToDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }


    public User userDtoToEntity(UserDto userDto){
        User userEntity = new User();
        userEntity.setId(userDto.id());
        userEntity.setName(userDto.nom());
        userEntity.setEmail(userDto.email());
        userEntity.setRole(userDto.role());
        return userEntity;
    }



}
