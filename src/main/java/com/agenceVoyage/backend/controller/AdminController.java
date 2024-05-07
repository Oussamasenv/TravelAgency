package com.agenceVoyage.backend.controller;

import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.service.implementations.*;
import com.agenceVoyage.backend.service.interfaces.*;
import com.agenceVoyage.backend.wrapper.HotelData;
import com.agenceVoyage.backend.wrapper.TravelData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AdminController {


    private final HotelService hotelService;
    private final FacilityService facilityService;
    private final TravelService travelService;
    private final ProgramService programService;
    private final AirplaneCompanyService airplaneCompanyService;



    public AdminController(
                           HotelServiceImp hotelServiceImp,
                           FacilityServiceImp flightServiceServiceImp,
                           TravelServiceImp travelServiceImp,
                           ProgramServiceImp programServiceImp,
                           AirplaneCompanyServiceImp airplaneCompanyServiceImp) {
        this.hotelService = hotelServiceImp;
        this.facilityService = flightServiceServiceImp;
        this.travelService = travelServiceImp;
        this.programService = programServiceImp;
        this.airplaneCompanyService = airplaneCompanyServiceImp;

    }


    @PostMapping("/createHotel")
    @Transactional
    public ResponseEntity<HotelData> createHotel(@Valid @RequestBody HotelData hotelData) {

            return ResponseEntity.ok(hotelService.createHotelWithRooms(hotelData));


    }


    @PostMapping("/createService")
    @Transactional
    public ResponseEntity<Facility> createService(@Valid @RequestBody Facility facility) {

            return ResponseEntity.ok(facilityService.createService(facility));

    }

    @PostMapping("/createTravel")
    @Transactional
    public ResponseEntity<TravelData> createTravel(
            @Valid @RequestBody TravelData travelData
    ) {
            return ResponseEntity.ok(travelService.createTravel(travelData));

    }

    @PostMapping("/createProgram")
    @Transactional
    public ResponseEntity<Program> saveProgram(@Valid @RequestBody Program program) {

        return ResponseEntity.ok(programService.saveProgram(program));

    }


    @PostMapping("/createAirplaneCompany")
    @Transactional
    public ResponseEntity<AirplaneCompany> saveAirplaneCompany(@Valid @RequestBody AirplaneCompany airplaneCompany) {

        return ResponseEntity.ok(airplaneCompanyService.saveAirplane(airplaneCompany));
    }

}
