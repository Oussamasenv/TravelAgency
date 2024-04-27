package com.agenceVoyage.backend.model;

import java.time.ZonedDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.sql.ast.tree.expression.Collation;


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
    @NotBlank
    private ZonedDateTime departure;

    @NotBlank
    private int duration;

    @Enumerated(EnumType.STRING)
    private FlightType type;

    @NotBlank
    private String desciption;

    @NotBlank
    private double initialPrice;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private FlightAvailibility availibility;

    private int groupesize;
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
    private Collection<Service> services;
}
