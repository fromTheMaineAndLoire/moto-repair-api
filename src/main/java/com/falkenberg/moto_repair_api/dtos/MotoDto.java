package com.falkenberg.moto_repair_api.dtos;

public record MotoDto(Long id,
                      String marque,
                      String model,
                      int year,
                      String immatriculation,
                      Long customer_id) {
}
