package com.agenceVoyage.backend.model;

import java.time.ZonedDateTime;
import java.util.Collection;

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

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String name;


    @NotNull
    @Enumerated(EnumType.STRING)
    private Continent continent;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Country country;

    @NotNull
    @FutureOrPresent
    private ZonedDateTime departure;


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


    @DecimalMin(value = "0.0")
    private double discountedPrice;

    @Enumerated(EnumType.STRING)
    private FlightAvailibility availability;

    @NotNull
    @Min(1)
    private int groupSize;

    @NotNull
    private int placesLeft;

    @ManyToMany
    @NotNull
    private Collection<AirplaneCompany> airplaneCompanies;

    @ManyToMany
    @NotNull
    private Collection<Program> programs;


    @ManyToMany
    public Collection<Facility> facilities;

    @ManyToMany
    @NotNull
    public Collection<Room> rooms;

}
