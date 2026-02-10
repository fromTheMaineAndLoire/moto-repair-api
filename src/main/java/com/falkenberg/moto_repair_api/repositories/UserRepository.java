package com.falkenberg.moto_repair_api.repositories;

import com.falkenberg.moto_repair_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
