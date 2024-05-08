package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;


@Getter
@Setter
public class ReservationDto {

    private long id;
    private double totalPricing;
    private LocalDateTime reservationDate;
    private LocalDateTime cancellationDate;
    private String paymentInfo;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    private User user;
    @NotNull
    private Collection<Traveler> travelers;
    @NotNull
    private Travel travel;
    @NotNull
    private Collection<Room> rooms;
    private Collection<Facility> facilities;
}
