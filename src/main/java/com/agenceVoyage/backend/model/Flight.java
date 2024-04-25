package com.agenceVoyage.backend.model;

import java.time.ZonedDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private ZonedDateTime departure;
    private int duration;

    @Enumerated(EnumType.STRING)
    private FlightType type;

    private String desciption;
    private double initialPrice;

    @Enumerated(EnumType.STRING)
    private FlightAvailibility availibility;

    private int groupesize;
    private int luggageCapacityPerReservation;

    @OneToMany(mappedBy = "flight")
    @JsonManagedReference
    private Collection<Reservation> reservationCollation;

    @OneToMany(mappedBy = "flight")
    @JsonManagedReference
    private Collection<AirplaneCompany> airplaneCompanyCollation;

    @ManyToMany
    private Collection<Program> programs;
}
