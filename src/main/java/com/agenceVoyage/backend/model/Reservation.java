package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private double id;
    @NotNull
    private double totalPricing;
    @NotNull
    private LocalDateTime reservationDate;
    @NotNull
    private LocalDateTime cancelationDate;
    @NotBlank
    private String paymentInfo;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "reservations")
    @JsonIgnore
    private Collection<Traveler> travelers;

    @ManyToOne
    @JsonIgnore
    private Flight flight;

    @OneToMany
    @JsonIgnore
    private Collection<Room> rooms;

    @ManyToMany
    @JsonIgnore
    private Collection<Facility> facilities;

}
