package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Collection;


@Getter
@Setter
public class TravelDto {

    private long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Continent continent;

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

    @DecimalMin(value = "0.0")
    private double discountedPrice;

    @Enumerated(EnumType.STRING)
    private FlightAvailibility availability;

    @NotNull
    @Min(1)
    private int groupSize;

    @NotNull
    private int placesLeft;



    @NotNull
    private Collection<AirplaneCompanyDto> airplaneCompanies;

    @NotNull
    private Collection<ProgramDto> programs;

    public Collection<Facility> facilities;


}
