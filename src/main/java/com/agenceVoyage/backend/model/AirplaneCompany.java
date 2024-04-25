package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AirplaneCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    private String name;

    @ManyToMany(mappedBy = "airplaneCompanies")
    private Collection<Service> services;

    @ManyToOne
    @JsonBackReference
    private Flight flight;

}
