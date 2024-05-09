package com.agenceVoyage.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomAvailability availability;

    @NotNull
    @Min(0)
    private int roomNumber;

    @NotNull
    @DecimalMin(value = "0.0")
    private double pricePerNight;


    @ManyToOne
    private Hotel hotel;

}
