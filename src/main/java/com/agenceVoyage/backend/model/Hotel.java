package com.agenceVoyage.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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

    @Size(min = 8, max = 50)
    private String name;
    @NotBlank
    private String location;
    @NotBlank
    private int starts;
    @NotBlank
    private int roomsNumber;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private Collection<Room> rooms;

    @ManyToMany
    @JsonIgnore
    private Collection<Service> services;

}
