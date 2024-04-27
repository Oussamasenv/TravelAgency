package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @NotBlank
    private double totalPricing;
    @NotBlank
    private LocalDateTime reservationDate;
    @NotBlank
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
    private Collection<Service> services;

}
