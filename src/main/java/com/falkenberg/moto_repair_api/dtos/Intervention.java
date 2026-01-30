package com.falkenberg.moto_repair_api.dtos;

import com.falkenberg.moto_repair_api.enums.Type;

public record Intervention(Long id,
                           Type type,
                           String comment,
                           int estimatedTime,
                           RepairingOrder repairingOrder) {
}
