package com.falkenberg.moto_repair_api.dtos;

import com.falkenberg.moto_repair_api.enums.Priority;
import com.falkenberg.moto_repair_api.enums.Status;

import java.time.LocalDateTime;

public record RepairingOrder(Long id,
                             String reference,
                             String description,
                             Status status,
                             Priority priority,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt,
                             Customer customer,
                             MotoCycle motoCycle,
                             User mecanician) {
}
