package com.agenceVoyage.backend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    @Size(min = 2, max = 30)
    private String name;
    @NotNull
    private double basePricePerDay;
//
//    @NotNull
//    private FacilityPricingType facilityPricingType;


//    @ManyToMany(mappedBy = "services")
//    private Collection<Reservation> reservations;
//
//    @ManyToMany
//    private Collection<AirplaneCompany> airplaneCompanies;
//
//    @ManyToMany
//    private Collection<Hotel> hotels;
}
