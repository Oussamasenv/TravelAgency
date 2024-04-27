package com.agenceVoyage.backend.controller;

import com.agenceVoyage.backend.designpatterns.strategy.DailyPricingStartegy;
import com.agenceVoyage.backend.designpatterns.strategy.DurationPricingStrategy;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.service.implementations.FacilityServiceImp;
import com.agenceVoyage.backend.service.implementations.FlightServiceImp;
import com.agenceVoyage.backend.service.implementations.HotelServiceImp;
import com.agenceVoyage.backend.service.implementations.RoomServiceImp;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import com.agenceVoyage.backend.service.interfaces.FlightService;
import com.agenceVoyage.backend.service.interfaces.HotelService;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import com.agenceVoyage.backend.wrapper.FlightData;
import com.agenceVoyage.backend.wrapper.HotelData;
import jakarta.validation.Valid;
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

    public AdminController(RoomServiceImp roomService,
                           HotelServiceImp hotelServiceImp,
                           FacilityServiceImp flightServiceServiceImp,
                           FlightServiceImp flightServiceImp){
        this.roomService = roomService;
        this.hotelService = hotelServiceImp;
        this.facilityService = flightServiceServiceImp;
        this.flightService = flightServiceImp;
    }




    @PostMapping("/createHotel")
    @Transactional
    public ResponseEntity<?> createHotel( @Valid @RequestBody HotelData hotelData) {

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
            return ResponseEntity.ok(hotel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
        }

    }


    @PostMapping("/createService")
    public ResponseEntity<?> createService(@Valid @RequestBody Facility facility) {
        try {

            facilityService.createService(facility);
            return ResponseEntity.ok(facility);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter valid information");
        }

    }

    @PostMapping("/createFlight")
    @Transactional
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightData flightData) {
        try {
            double flightPricing;
            Flight flight = flightData.getFlight();
            ConcurrentLinkedDeque<Facility> facilities= flightData.getFacilities();

            flightPricing = flight.getInitialPrice();
            System.out.println(flightPricing);

            // using strategy design pattern
            for (Facility facility : facilities) {

                if(flight.getFacilityPricingType() == FacilityPricingType.LONG_DURATION){
                    flightPricing = flightPricing + DurationPricingStrategy.calculatePricing(flight.getDuration(), facility.getBasePricePerDay(), 10);
                    System.out.println(flightPricing);
                } else if (flight.getFacilityPricingType() == FacilityPricingType.SPECIFIC_DURATION){
                    flightPricing = flightPricing + DailyPricingStartegy.calculatePricing(flight.getDuration(), facility.getBasePricePerDay(), flight.getFacilityDays());
                    System.out.println(flightPricing);
                }

            }

            ZonedDateTime returnDate = flight.getDeparture().plusDays(flight.getDuration());
            flight.setInitialPrice(flightPricing);
            System.out.println(flight.getInitialPrice());
            flight.setFacilities(facilities);
            flight.setAvailability(FlightAvailibility.AVAILABLE);
            flight.setReturnDate(returnDate);

            return ResponseEntity.ok(flightService.createFlight(flight));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
        }

    }





}
