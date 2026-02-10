package com.falkenberg.moto_repair_api.repositories;

import com.falkenberg.moto_repair_api.entities.RepairingOrder;
import com.falkenberg.moto_repair_api.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairingOrderRepository extends JpaRepository<RepairingOrder,Long> {

    @Modifying
    @Query("""
            update RepairingOrder r
            set r.status=:status
            where r.id=:id
            """)
    int updateStatusById(@Param("id") Long id, @Param("status") Status status);
}
