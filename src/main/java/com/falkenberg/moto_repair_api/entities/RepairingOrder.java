package com.falkenberg.moto_repair_api.entities;

import com.falkenberg.moto_repair_api.enums.Priority;
import com.falkenberg.moto_repair_api.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bon_reparation")
@Getter
@Setter
public class RepairingOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "description_probleme")
    private String problemDescription;

    @Column(name = "statut")
    private Status status;

    @Column(name = "priorite")
    private Priority priority;

    @Column(name = "date_creation")
    private LocalDateTime createdAt;

    @Column(name = "date_mise_a_jour")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "moto_id", unique = true)
    private Moto moto;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
