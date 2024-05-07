package com.agenceVoyage.backend.controller;

import com.agenceVoyage.backend.designpatterns.facade.ReservationFacade;
import com.agenceVoyage.backend.wrapper.ReservationData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final ReservationFacade reservationFacade;


    public UserController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }


    @PostMapping("/createReservation")
    @Transactional
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationData reservationData) {

            reservationFacade.createReservation(reservationData);
            return ResponseEntity.ok("successfull reservation");


    }

    @PostMapping("/cancelReservation")
    @Transactional
    public ResponseEntity<?> cancelReservation(@Valid @RequestBody long id) {

            reservationFacade.CancelReservation(id);
            return ResponseEntity.ok("reservation canceled");

    }

}
