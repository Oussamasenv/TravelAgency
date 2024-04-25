package com.agenceVoyage.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    private String name;
    private int age;
    private String identity;
    private String luggage;

    @ManyToMany
    private Collection<Reservation> reservations;
}
