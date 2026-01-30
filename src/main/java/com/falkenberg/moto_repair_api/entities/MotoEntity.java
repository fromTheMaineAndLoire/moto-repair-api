package com.falkenberg.moto_repair_api.entities;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Moto")
@Getter
@Setter
public class MotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "marque")
    private String marque;

    @Column(name = "modele")
    private String modele;

    @Column(name = "annee")
    private int annee;

    @Column(name = "immatriculation")
    private String immatriculation;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", unique = true)
    private CustomerEntity client;
}
