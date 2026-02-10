package com.falkenberg.moto_repair_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CLIENT")
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="email")
    private String email ;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

}
