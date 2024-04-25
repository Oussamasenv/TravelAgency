package com.agenceVoyage.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private RoomAvailability availability;


    private int pricePerNight;

    @ManyToOne
    @JsonBackReference
    private Reservation reservation;

    @ManyToOne
    @JsonBackReference
    private Hotel hotel;

}
