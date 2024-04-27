package com.agenceVoyage.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private RoomAvailability availability;

    @NotNull
    private int roomNumber;
    @NotNull
    private int pricePerNight;


    @ManyToOne
    private Hotel hotel;



}
