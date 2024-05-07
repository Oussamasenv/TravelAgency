package com.agenceVoyage.backend.designpatterns.facade;

import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.service.implementations.*;
import com.agenceVoyage.backend.service.interfaces.*;
import com.agenceVoyage.backend.wrapper.ReservationData;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;




@Component
public class ReservationFacade {


    private final FacilityService facilityService;

    private final RoomService roomService;

    private final UserService userService;

    private final ReservationService reservationService;

    private final TravelerService travelerService;

    private final TravelService travelService;




    //preferring constructor injection over field injection for best practices usage
    public ReservationFacade(
            RoomServiceImp roomServiceImp,
            ReservationServiceImp reservationServiceImp,
            TravelerServiceImp travelerServiceImp,
            FacilityServiceImp facilityServiceImp,
            UserServiceImp userDetailsServiceImp,
            TravelServiceImp travelServiceImp

    ) {
        this.roomService = roomServiceImp;
        this.reservationService = reservationServiceImp;
        this.travelerService = travelerServiceImp;
        this.facilityService = facilityServiceImp;
        this.userService = userDetailsServiceImp;
        this.travelService = travelServiceImp;
    }


    public Reservation createReservation(ReservationData reservationData) {


        Travel travel = reservationData.getTravel();
        ConcurrentLinkedDeque<Room> rooms = reservationData.getRooms();
        ConcurrentLinkedDeque<Traveler> travelers = reservationData.getTravelers();
        ConcurrentLinkedDeque<Facility> facilities = reservationData.getFacilities();
        User user = userService.getUser(reservationData.getUserId());



        // 1.Flight necessary logic for reservation
        travelService.setTravelToReserve(travel, travelers.size());

        // 2. Room necessary logic for reservation
        double roomsPricing = roomService.setRoomsToReserve(rooms, travel.getDuration());

        // 3. Traveler necessary logic for reservation
        // save travelers in db;
        travelerService.setTravelersToReservation(travelers);

        // 4. Facility necessary logic for reservation
        facilityService.setFacilitiesToReserve(facilities, travel);

        // 5. Reservation necessary logic for reservation
        Reservation reservation = reservationService.setReservationToReserve(travel, roomsPricing, facilities, travelers, rooms, user);

        // 6. User necessary logic for reservation
        userService.setUserToReservation(user, reservation);

        return reservation;

        }

        public Reservation CancelReservation(long id) {

            Reservation reservation = reservationService.getReservationById(id);

            Travel travel = reservation.getTravel();
            Collection<Room> roomsVar = reservation.getRooms();
            ConcurrentLinkedDeque<Room> rooms = new ConcurrentLinkedDeque<Room>(roomsVar);

            // 1. travel necessary logic for cancel reservation
            travelService.setTravelToCancelReservation(travel, reservation.getTravelers().size());

            // 2. room necessary logic for cancel reservation
            roomService.setRoomsToCancelReservation(rooms);

            // 3. room necessary logic for cancel reservation
            reservationService.cancelReservation(id);

            return reservation;

        }



}
