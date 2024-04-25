package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private double totalPricing;
    private LocalDateTime reservationDate;
    private LocalDateTime cancelationDate;
    private String paymentInfo;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToMany(mappedBy = "reservations")
    private Collection<Traveler> travelers;

    @ManyToOne
    @JsonBackReference
    private Flight flight;

    @OneToMany(mappedBy = "reservation")
    @JsonManagedReference
    private Collection<Room> rooms;

    @ManyToMany
    private Collection<Service> services;

}
