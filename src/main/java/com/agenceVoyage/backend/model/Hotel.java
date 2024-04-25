package com.agenceVoyage.backend.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    private String name;
    private String location;
    private int starts;
    private int roomsNumber;

    @OneToMany(mappedBy = "hotel")
    @JsonManagedReference
    private Collection<Room> rooms;

    @ManyToMany
    private Collection<Service> services;

}
