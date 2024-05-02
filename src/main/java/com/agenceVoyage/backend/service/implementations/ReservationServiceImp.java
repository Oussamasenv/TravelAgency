package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.designpatterns.facade.ReservationFacade;
import com.agenceVoyage.backend.dto.UserDto;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.repository.ReservationRepository;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import com.agenceVoyage.backend.service.interfaces.ReservationService;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service

@NoArgsConstructor
public class ReservationServiceImp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FacilityService facilityService;

    public ReservationServiceImp(
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

            Flight flight,
            double roomsPricing,
            ConcurrentLinkedDeque<Facility> facilities,
            ConcurrentLinkedDeque<Traveler> travelers,
            ConcurrentLinkedDeque<Room> rooms,
            User user)

    {
        Reservation reservation = new Reservation();

        double totalPricing = flight.getInitialPrice();
        totalPricing += facilityService.setFacilitiesToReserve(facilities, flight);
        totalPricing += roomsPricing;

        reservation.setTotalPricing(totalPricing);
        reservation.setReservationDate(LocalDateTime.now());

        reservation.setRooms(rooms);
        reservation.setTravelers(travelers);
        reservation.setFacilities(facilities);
        reservation.setUser(user);
        reservation.setFlight(flight);

        return reservationRepository.save(reservation);

    }

}
