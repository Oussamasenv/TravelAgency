package com.agenceVoyage.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min = 8, max = 50)
    private String name;
    @NotBlank
    private String location;
    @NotNull
    private int starts;
    @NotNull
    private int roomsNumber;

    @OneToMany(mappedBy = "hotel")
    private Collection<Room> rooms;

}
