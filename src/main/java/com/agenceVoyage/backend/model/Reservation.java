package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @DecimalMin(value = "0.0")
    private double totalPricing;

    @NotNull
    private LocalDateTime reservationDate;

    private LocalDateTime cancellationDate;

    private String paymentInfo;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @NotNull
    private Collection<Traveler> travelers;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Travel travel;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @NotNull
    private Collection<Room> rooms;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Collection<Facility> facilities;

}
