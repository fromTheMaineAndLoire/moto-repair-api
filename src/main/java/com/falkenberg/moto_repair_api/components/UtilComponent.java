package com.falkenberg.moto_repair_api.components;

import com.falkenberg.moto_repair_api.dtos.Customer;
import com.falkenberg.moto_repair_api.dtos.MotoCycle;
import com.falkenberg.moto_repair_api.dtos.RepairingOrder;
import com.falkenberg.moto_repair_api.dtos.User;
import com.falkenberg.moto_repair_api.entities.CustomerEntity;
import com.falkenberg.moto_repair_api.entities.MotoEntity;
import com.falkenberg.moto_repair_api.entities.RepairingOrderEntity;
import com.falkenberg.moto_repair_api.entities.UserEntity;
import com.falkenberg.moto_repair_api.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class UtilComponent {

    public CustomerEntity customerDtoToEntity(Customer customer){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail(customer.email());
        customerEntity.setName(customer.name());
        customerEntity.setPhone(customer.phone());

        return customerEntity;
    }

    public Customer customerEntityToDto(CustomerEntity customerEntity){
        return new Customer(customerEntity.getId(),
                customerEntity.getName(),
                customerEntity.getPhone(),
                customerEntity.getEmail());
    }

    public MotoCycle motoCycleEntityToDto(MotoEntity motoEntity) {
        return new MotoCycle(motoEntity.getId(),
                motoEntity.getMarque(),
                motoEntity.getModele(),
                motoEntity.getAnnee(),
                motoEntity.getImmatriculation(),
                customerEntityToDto(motoEntity.getClient()));
    }

    public MotoEntity motoCycleDtoToEntity(MotoCycle motoCycle){
        MotoEntity motoEntity = new MotoEntity();
        motoEntity.setMarque(motoCycle.marque());
        motoEntity.setModele(motoCycle.model());
        motoEntity.setAnnee(motoCycle.year());
        motoEntity.setImmatriculation(motoCycle.immatriculation());
        motoEntity.setClient(customerDtoToEntity(motoCycle.customer()));

        return motoEntity;
    }

    public RepairingOrder repairingOrderEntityToDto(RepairingOrderEntity repairingOrderEntity){
        return new RepairingOrder(
                repairingOrderEntity.getId(),
                repairingOrderEntity.getReference(),
                repairingOrderEntity.getProblemDescription(),
                repairingOrderEntity.getStatus(),
                repairingOrderEntity.getPriority(),
                repairingOrderEntity.getCreatedAt(),
                repairingOrderEntity.getUpdatedAt(),
                customerEntityToDto(repairingOrderEntity.getCustomerEntity()),
                motoCycleEntityToDto(repairingOrderEntity.getMotoEntity()),
                userToDto(repairingOrderEntity.getUserEntity())
        );
    }

    public RepairingOrderEntity repairingOrderToEntity(RepairingOrder repairingOrder){
        RepairingOrderEntity repairingOrderEntity =  new RepairingOrderEntity();

        repairingOrderEntity.setReference(repairingOrder.reference());
        repairingOrderEntity.setStatus(repairingOrder.status());
        repairingOrderEntity.setPriority(repairingOrder.priority());
        repairingOrderEntity.setCreatedAt(repairingOrder.createdAt());
        repairingOrderEntity.setUpdatedAt(repairingOrder.updatedAt());
        repairingOrderEntity.setCustomerEntity(customerDtoToEntity(repairingOrder.customer()));
        repairingOrderEntity.setMotoEntity(motoCycleDtoToEntity(repairingOrder.motoCycle()));
        repairingOrderEntity.setUserEntity(userDtoToEntity(repairingOrder.mecanician()));

        return repairingOrderEntity;
    }


    public User userToDto(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getRole()
        );
    }


    public UserEntity userDtoToEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.id());
        userEntity.setName(user.nom());
        userEntity.setEmail(user.email());
        userEntity.setRole(user.role());
        return userEntity;
    }



}
