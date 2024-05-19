package com.agenceVoyage.backend.controller.admin;

import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.dto.TravelerDto;
import com.agenceVoyage.backend.service.implementations.TravelServiceImp;
import com.agenceVoyage.backend.service.implementations.TravelerServiceImp;
import com.agenceVoyage.backend.service.interfaces.TravelService;
import com.agenceVoyage.backend.service.interfaces.TravelerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class TravelerAdminController {


    private final TravelerService travelerService;

    public TravelerAdminController(TravelerServiceImp travelerServiceImp) {
        this.travelerService = travelerServiceImp;
    }


    @PostMapping("/addTraveler")
    @Transactional
    public ResponseEntity<?> addTraveler(
            @Valid @RequestBody TravelerDto travelerDto,
            BindingResult bindingResult){

        if(bindingResult.hasFieldErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));
        }

        return ResponseEntity.ok(travelerService.save(travelerDto));
    }

    @DeleteMapping("/travelers/{id}")
    @Transactional
    public void removeTraveler(@PathVariable long id){
        travelerService.deleteTraveler(id);
    }

    @GetMapping("/travelers")
    public ResponseEntity<?> getAllTravelers(){
        return ResponseEntity.ok(travelerService.getTravelers());
    }

    @PutMapping("/travelers/{id}")
    @Transactional
    public ResponseEntity<?> updateTraveler(
            @Valid @RequestBody TravelerDto travelerDto,
            BindingResult bindingResult,
            @PathVariable long id){

        if(bindingResult.hasFieldErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));
        }

        travelerService.updateTraveler(id, travelerDto);

        return ResponseEntity.ok(travelerDto);
    }



}
