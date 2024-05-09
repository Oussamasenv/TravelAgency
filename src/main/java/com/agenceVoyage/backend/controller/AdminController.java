package com.agenceVoyage.backend.controller;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.service.implementations.*;
import com.agenceVoyage.backend.service.interfaces.*;
import com.agenceVoyage.backend.wrapper.HotelData;
import com.agenceVoyage.backend.wrapper.TravelData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public ResponseEntity<?> createHotel(
            @Valid @RequestBody HotelData hotelData,
            BindingResult bindingResult) {

        try {

            if (bindingResult.hasFieldErrors()) {

                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                Map<String, String> errorMap = new HashMap<String, String>();

                for (FieldError fieldError : fieldErrors) {
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }

                return ResponseEntity.badRequest().body(errorMap);

            }

            return ResponseEntity.ok(hotelService.createHotelWithRooms(hotelData));
        } catch (Error e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("internal error");
        }


    }


    @PostMapping("/createFacility")
    @Transactional
    public ResponseEntity<?> createService(
            @Valid @RequestBody FacilityDto facilityDto,
            BindingResult bindingResult
            ) {

        if(bindingResult.hasFieldErrors()){

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<String, String>();

            for(FieldError fieldError : fieldErrors){
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);

        }

        return ResponseEntity.ok(facilityService.createService(facilityDto));

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
    public ResponseEntity<?> saveProgram(@Valid @RequestBody ProgramDto programDto) {

        return ResponseEntity.ok(programService.saveProgram(programDto));

    }


    @PostMapping("/createAirplaneCompany")
    @Transactional
    public ResponseEntity<?> saveAirplaneCompany(
            @Valid @RequestBody AirplaneCompanyDto airplaneCompanyDto,
            BindingResult bindingResult) {

        return ResponseEntity.ok(airplaneCompanyService.saveAirplane(airplaneCompanyDto));
    }

}
