package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Collection;


@Getter
@Setter
public class TravelDto {

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

    @NotNull
    private AirplaneCompanyDto airplaneCompanyDto;

    private Collection<ProgramDto> programsDto;

}
