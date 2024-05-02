package com.agenceVoyage.backend.designpatterns.facade;

import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.service.UserDetailsServiceImp;
import com.agenceVoyage.backend.service.implementations.*;
import com.agenceVoyage.backend.service.interfaces.*;
import com.agenceVoyage.backend.wrapper.ReservationData;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;




@Component
public class ReservationFacade {

    @Autowired
    private FacilityService facilityService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private TravelerService travelerService;



//    public ReservationFacade(
//            FlightServiceImp flightServiceImp,
//            RoomServiceImp roomServiceImp,
//            ReservationServiceImp reservationServiceImp,
//            TravelerServiceImp travelerServiceImp,
//            FacilityServiceImp facilityServiceImp,
//            UserServiceImp userDetailsServiceImp
//
//    ) {
//        this.flightService = flightServiceImp;
//        this.roomService = roomServiceImp;
//        this.reservationService = reservationServiceImp;
//        this.travelerService = travelerServiceImp;
//        this.facilityService = facilityServiceImp;
//        this.userService = userDetailsServiceImp;
//    }


    public Reservation createReservation(ReservationData reservationData) {

//        Flight flight = flightService.getFlight(reservationData.getFlightId());
//
//        List<Room> vRooms = roomService.findAllByIds(reservationData.getRoomsIds());
//        ConcurrentLinkedDeque<Room> rooms = new ConcurrentLinkedDeque<Room>(vRooms);
//
//        ConcurrentLinkedDeque<Traveler> travelers = reservationData.getTravelers();
//
//        List<Facility> vFacilities = facilityService.findAllByIds(reservationData.getFacilitiesIds());
//        ConcurrentLinkedDeque<Facility> facilities = new ConcurrentLinkedDeque<Facility>(vFacilities);
//

        Flight flight = reservationData.getFlight();
        ConcurrentLinkedDeque<Room> rooms = reservationData.getRooms();
        ConcurrentLinkedDeque<Traveler> travelers = reservationData.getTravelers();
        ConcurrentLinkedDeque<Facility> facilities = reservationData.getFacilities();
        User user = userService.getUser(reservationData.getUserId());


//        User user = userService.getUser(reservationData.getUserId());



        // 1.Flight necessary logic for reservation
        flightService.setFlightToReserve(flight, travelers.size());

        // 2. Room necessary logic for reservation
        double roomsPricing = roomService.setRoomsToReserve(rooms, flight.getDuration());

        // 3. Traveler necessary logic for reservation
        // save travelers in db;
        travelerService.setTravelersToReservation(travelers);






        // 4. Facility necessary logic for reservation
        facilityService.setFacilitiesToReserve(facilities, flight);


        Reservation reservation = reservationService.setReservationToReserve(flight, roomsPricing, facilities, travelers, rooms, user);


        // 5. User necessary logic for reservation
        userService.setUserToReservation(user, reservation);

        return reservation;

        }



}
