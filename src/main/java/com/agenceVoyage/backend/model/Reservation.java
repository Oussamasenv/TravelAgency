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

    @OneToOne
    @NotNull
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @NotNull
    private Collection<Traveler> travelers;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @NotNull
    private Travel travel;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @NotNull
    private Collection<Room> rooms;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Facility> facilities;

}
