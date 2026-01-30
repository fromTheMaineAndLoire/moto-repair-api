package com.falkenberg.moto_repair_api.repositories;

import com.falkenberg.moto_repair_api.entities.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoRepository extends JpaRepository<MotoEntity,Long> {
}
