package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.service.implementations.ReservationServiceImp;
import com.agenceVoyage.backend.service.interfaces.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ReservationAdminController {


    private final ReservationService reservationService;

    public ReservationAdminController(ReservationServiceImp reservationServiceImp) {
        this.reservationService = reservationServiceImp;
    }


    @GetMapping("/admin/reservations")
    public ResponseEntity<?> getReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

}
