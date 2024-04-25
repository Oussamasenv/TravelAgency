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

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    private String name;
    private String pricingType;

    @ManyToMany(mappedBy = "services")
    private Collection<Reservation> reservations;

    @ManyToMany
    private Collection<AirplaneCompany> airplaneCompanies;

    @ManyToMany
    private Collection<Hotel> hotels;
}
