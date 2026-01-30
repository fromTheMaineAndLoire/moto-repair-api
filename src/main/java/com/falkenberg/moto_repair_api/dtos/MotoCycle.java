package com.falkenberg.moto_repair_api.dtos;

public record MotoCycle(Long id,
                        String marque,
                        String model,
                        int year,
                        String immatriculation,
                        Customer customer) {
}
