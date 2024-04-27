package com.agenceVoyage.backend.model;

import java.time.ZonedDateTime;
import java.util.Collection;

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
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    @NotNull
    private ZonedDateTime departure;

    @NotNull
    private int duration;

    @Enumerated(EnumType.STRING)
    private FlightType type;

    @NotBlank
    private String desciption;

    @NotNull
    private double initialPrice;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private FlightAvailibility availibility;
    @NotNull
    private int groupesize;
    @NotNull
    private int luggageCapacityPerReservation;

    @OneToMany(mappedBy = "flight")
    private Collection<Reservation> reservationCollation;

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
    private Collection<FlightService> flightServices;
}
