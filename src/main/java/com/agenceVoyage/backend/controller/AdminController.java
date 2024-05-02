package com.agenceVoyage.backend.controller;

import com.agenceVoyage.backend.designpatterns.facade.ReservationFacade;
import com.agenceVoyage.backend.designpatterns.strategy.DailyPricingStartegy;
import com.agenceVoyage.backend.designpatterns.strategy.DurationPricingStrategy;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.service.UserDetailsServiceImp;
import com.agenceVoyage.backend.service.implementations.*;
import com.agenceVoyage.backend.service.interfaces.*;
import com.agenceVoyage.backend.wrapper.FlightData;
import com.agenceVoyage.backend.wrapper.HotelData;
import com.agenceVoyage.backend.wrapper.ReservationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
public class AdminController {


    private final RoomService roomService;
    private final HotelService hotelService;
    private final FacilityService facilityService;
    private final FlightService flightService;
    private final ReservationFacade reservationFacade;


    public AdminController(RoomServiceImp roomService,
                           HotelServiceImp hotelServiceImp,
                           FacilityServiceImp flightServiceServiceImp,
                           FlightServiceImp flightServiceImp,
                           ReservationFacade reservationFacade) {
        this.roomService = roomService;
        this.hotelService = hotelServiceImp;
        this.facilityService = flightServiceServiceImp;
        this.flightService = flightServiceImp;
        this.reservationFacade = reservationFacade;
//        this.userService = userServiceImp;
//        this.reservationService = reservationServiceImp;
//        this.travelerService = travelerServiceImp;
    }


    @PostMapping("/createHotel")
    @Transactional
    public ResponseEntity<?> createHotel(@Valid @RequestBody HotelData hotelData) {

        try {
            Hotel hotel = hotelData.getHotel();
            ConcurrentLinkedDeque<Room> rooms = hotelData.getRooms();
            hotel.setRooms(rooms);
            hotelService.createHotel(hotel);

            for (Room room : rooms) {
                room.setAvailability(RoomAvailability.AVAILABLE);
                room.setHotel(hotel);
                roomService.createRoom(room);
            }



            return ResponseEntity.ok("hotel and rooms added successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
        }

    }


    @PostMapping("/createService")
    @Transactional
    public ResponseEntity<?> createService(@Valid @RequestBody Facility facility) {
        try {

            facilityService.createService(facility);
            return ResponseEntity.ok("facility created successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter valid information");
        }

    }

    @PostMapping("/createFlight")
    @Transactional
    public ResponseEntity<?> createFlight(@Valid @RequestBody Flight flight) {
        try {


            System.out.println(flight.getDeparture());

            if(flight.getDeparture() == null || flight.getDuration() <= 0){
                throw new Exception("enter valid information");

            }

            ZonedDateTime returnDate = flight.getDeparture().plusDays(flight.getDuration());
            flight.setAvailability(FlightAvailibility.AVAILABLE);
            flight.setPlacesLeft(flight.getGroupSize());
            flight.setReturnDate(returnDate);
            System.out.println("maybe null value:" + returnDate);

            System.out.println(flight);

            flightService.createFlight(flight);

            return ResponseEntity.ok("flight created successfully");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("check validation");
        }

    }


    @PostMapping("/createReservation")
    @Transactional
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationData reservationData) {

        try {

            reservationFacade.createReservation(reservationData);


            return ResponseEntity.ok("successfull reservation");

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");

        }

    }

}
