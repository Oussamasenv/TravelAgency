package com.agenceVoyage.backend.model;

import java.time.ZonedDateTime;
import java.util.Collection;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
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

    private int placesLeft;

    @ManyToOne
    @JoinColumn(
            name = "airplane_company_id",
            referencedColumnName = "id"
    )
    @NotNull
    private AirplaneCompany airplaneCompany;

    @OneToMany
    private Collection<Program> programs;



}
