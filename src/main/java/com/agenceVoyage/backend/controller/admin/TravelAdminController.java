package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.service.implementations.TravelServiceImp;
import com.agenceVoyage.backend.service.interfaces.TravelService;
import com.agenceVoyage.backend.wrapper.TravelData;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class TravelAdminController {


    private final TravelService travelService;

    public TravelAdminController(TravelServiceImp travelServiceImp) {
        this.travelService = travelServiceImp;
    }


    @PostMapping("/createTravel")
    @Transactional
    public ResponseEntity<?> createTravel(
            @Valid @RequestBody TravelData travelData,
            BindingResult bindingResult
    ) {


        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));

        }


        return ResponseEntity.ok(travelService.createTravel(travelData));

    }

    @GetMapping("/travels")
    public ResponseEntity<?> getAllTravels(){

        return ResponseEntity.ok(travelService.getTravels());
    }

    @PutMapping("/travels/{id}")
    @Transactional
    public ResponseEntity<?> updateTravel(
            @PathVariable long id,
            @Valid @RequestBody TravelDto travelDto,
            BindingResult bindingResult){

        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));

        }

        travelService.updateTravel(id, travelDto);

        return ResponseEntity.ok(travelDto);

    }

    @DeleteMapping("/travels/{id}")
    @Transactional
    public ResponseEntity<?> deleteTravel(@Valid @PathVariable long id){

        travelService.deleteTravel(id);
        return ResponseEntity.ok("Travel deleted successfully");
    }





}
