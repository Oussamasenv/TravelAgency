package com.agenceVoyage.backend.controller.admin;

import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.service.implementations.FacilityServiceImp;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class FacilityAdminController {


    private final FacilityService facilityService;

    public FacilityAdminController(FacilityServiceImp facilityServiceImp) {
        this.facilityService = facilityServiceImp;
    }


    @PostMapping("/createFacility")
    @Transactional
    public ResponseEntity<?> createService(
            @Valid @RequestBody FacilityDto facilityDto,
            BindingResult bindingResult
    ) {

        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));

        }

        return ResponseEntity.ok(facilityService.createService(facilityDto));

    }

    @GetMapping("/facilities")
    public ResponseEntity<?> getFacilities(){
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }

    @PutMapping("/facilities/{id}")
    @Transactional
    public ResponseEntity<?> updateFacility(
            @PathVariable long id,
            @Valid @RequestBody FacilityDto facilityDto,
            BindingResult bindingResult
    ) {

        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));

        }

        facilityService.updateFacility(id, facilityDto);

        return ResponseEntity.ok(facilityDto);
    }


    @DeleteMapping("/facilities/{id}")
    @Transactional
    public ResponseEntity<?> deleteFacility(@PathVariable long id){
        facilityService.deleteFacility(id);
        return ResponseEntity.ok("facility deleted successfully");
    }

}
