package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.*;

import java.util.concurrent.ConcurrentLinkedDeque;

public interface ReservationService {
    public Reservation save(Reservation reservation);

    public Reservation setReservationToReserve(
            Travel travel,
            double roomsPricing,
            ConcurrentLinkedDeque<Facility> facilities,
            ConcurrentLinkedDeque<Traveler> travelers,
            ConcurrentLinkedDeque<Room> rooms,
            User user);

    public Reservation cancelReservation(long id);

    public Reservation getReservationById(long id);
}
