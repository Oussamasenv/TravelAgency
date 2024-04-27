package com.agenceVoyage.backend.controller;

import com.agenceVoyage.backend.model.FlightService;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.Room;
import com.agenceVoyage.backend.model.RoomAvailability;
import com.agenceVoyage.backend.service.implementations.FlightServiceServiceImp;
import com.agenceVoyage.backend.service.implementations.HotelServiceImp;
import com.agenceVoyage.backend.service.implementations.RoomServiceImp;
import com.agenceVoyage.backend.service.interfaces.FlightServiceService;
import com.agenceVoyage.backend.service.interfaces.HotelService;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import com.agenceVoyage.backend.wrapper.HotelData;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
public class AdminController {


    private final RoomService roomService;
    private final HotelService hotelService;
    private final FlightServiceService flightServiceService;

    public AdminController(RoomServiceImp roomService,
                           HotelServiceImp hotelServiceImp,
                           FlightServiceServiceImp flightServiceServiceImp){
        this.roomService = roomService;
        this.hotelService = hotelServiceImp;
        this.flightServiceService = flightServiceServiceImp;
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
    public ResponseEntity<?> createService(@Valid @RequestBody FlightService flightService) {
        try {

            flightServiceService.createService(flightService);
            return ResponseEntity.ok(flightService);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter valid information");
        }
    }





}
