package com.falkenberg.moto_repair_api.dtos;


public record CustomerDto(Long id,
                          String name,
                          String phone,
                          String email) {
}
