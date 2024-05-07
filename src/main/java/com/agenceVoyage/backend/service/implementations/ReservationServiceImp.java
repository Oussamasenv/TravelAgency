package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.repository.ReservationRepository;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import com.agenceVoyage.backend.service.interfaces.ReservationService;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import com.agenceVoyage.backend.wrapper.ReservationData;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service


public class ReservationServiceImp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    private final FacilityService facilityService;


    public ReservationServiceImp(
            RoomServiceImp roomServiceImp,
            FacilityServiceImp facilityServiceImp
    ){
        this.facilityService = facilityServiceImp;
    }




    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation setReservationToReserve(

            Travel travel,
            double roomsPricing,
            ConcurrentLinkedDeque<Facility> facilities,
            ConcurrentLinkedDeque<Traveler> travelers,
            ConcurrentLinkedDeque<Room> rooms,
            User user)

    {
        Reservation reservation = new Reservation();

        double totalPricing = travel.getInitialPrice();
        totalPricing += facilityService.setFacilitiesToReserve(facilities, travel);
        totalPricing += roomsPricing;

        reservation.setTotalPricing(totalPricing);
        reservation.setReservationDate(LocalDateTime.now());

        reservation.setRooms(rooms);
        reservation.setTravelers(travelers);
        reservation.setFacilities(facilities);
        reservation.setUser(user);
        reservation.setTravel(travel);
        reservation.setReservationStatus(ReservationStatus.RESERVATION_PASSED);

        return reservationRepository.save(reservation);

    }

    @Override
    public Reservation cancelReservation(long id) {

        Reservation reservation = reservationRepository.getReferenceById(id);

        long daysUntilDeparture = ChronoUnit.DAYS.between(reservation.getTravel().getDeparture(), LocalDateTime.now());


        if(daysUntilDeparture > 7) {
            reservation.setReservationStatus(ReservationStatus.RESERVATION_CANCELED);
            reservationRepository.save(reservation);
            return reservation;
        } else {
            throw new RuntimeException("You can't cancel a reservation until 7 days before departure");
        }
    }

    @Override
    public Reservation getReservationById(long id) {
        return reservationRepository.getReferenceById(id);
    }


}
