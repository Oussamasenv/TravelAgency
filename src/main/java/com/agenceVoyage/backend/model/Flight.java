package com.agenceVoyage.backend.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private ZonedDateTime departure;

    @NotNull
    private int duration;


    private ZonedDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private FlightType type;

    private int facilityDays;

    @NotBlank
    private String description;

    @NotNull
    private double initialPrice;

    @Enumerated(EnumType.STRING)
    private FlightAvailibility availability;

    @NotNull
    private int groupSize;

    @NotNull
    private int placesLeft;

    private int luggageCapacityPerReservation;



    @OneToMany(mappedBy = "flight")
    private Collection<Reservation> reservations;

    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private Collection<AirplaneCompany> airplaneCompanyCollation;

    @ManyToMany
    @JsonIgnore
    private Collection<Program> programs;

    @OneToMany
    @JsonIgnore
    private Collection<Room> rooms;

    @OneToMany
    @JsonIgnore
    private Collection<Facility> facilities;
}
