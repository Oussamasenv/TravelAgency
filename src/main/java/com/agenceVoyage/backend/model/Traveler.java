package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 50)
    private String name;
    @Size(min = 2, max = 50)
    private String lastName;
    @NotBlank
    private int age;
    @NotBlank
    private String identity;
    @NotBlank
    private String luggage;

    @ManyToMany
    private Collection<Reservation> reservations;
}
