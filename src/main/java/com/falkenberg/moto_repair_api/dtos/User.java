package com.falkenberg.moto_repair_api.dtos;

import com.falkenberg.moto_repair_api.enums.Role;

public record User(Long id, String email, String nom, Role role) {
}
