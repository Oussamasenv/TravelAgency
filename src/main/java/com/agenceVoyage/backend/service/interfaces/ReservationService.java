package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.*;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

public interface ReservationService {
    public Reservation save(Reservation reservation);

    public Reservation setReservationToReserve(
            Flight flight,
            double roomsPricing,
            ConcurrentLinkedDeque<Facility> facilities,
            ConcurrentLinkedDeque<Traveler> travelers,
            ConcurrentLinkedDeque<Room> rooms,
            User user);
}
