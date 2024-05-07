package com.agenceVoyage.backend.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @FutureOrPresent
    private ZonedDateTime departure;

    @NotNull
    @Min(1)
    private int duration;

    private ZonedDateTime returnDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FlightType type;

    private int facilityDays;

    @NotBlank
    private String description;

    @NotNull
    @DecimalMin(value = "0.0")
    private double initialPrice;

    @Enumerated(EnumType.STRING)
    private FlightAvailibility availability;

    @NotNull
    @Min(1)
    private int groupSize;

    @NotNull
    private int placesLeft;



    @OneToMany(mappedBy = "travel")
    private Collection<Reservation> reservations;

    @ManyToOne
    @JsonIgnore
    @NotNull
    private AirplaneCompany airplaneCompany;

    @OneToMany
    @JsonIgnore
    private Collection<Program> programs;

    @OneToMany
    @JsonIgnore
    private Collection<Room> rooms;

    @OneToMany
    @JsonIgnore
    private Collection<Facility> facilities;
}
