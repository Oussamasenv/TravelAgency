package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.criteriaRepositories.PageProperties;
import com.agenceVoyage.backend.criteriaRepositories.travelCq.TravelSearchCriteria;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.Travel;
import com.agenceVoyage.backend.service.implementations.TravelServiceImp;
import com.agenceVoyage.backend.service.interfaces.TravelService;
import com.agenceVoyage.backend.wrapper.TravelData;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class TravelAdminController {


    private final TravelService travelService;
    private final ModelMapper modelMapper;

    public TravelAdminController(TravelServiceImp travelServiceImp, ModelMapper modelMapper) {
        this.travelService = travelServiceImp;
        this.modelMapper = modelMapper;
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

        travelService.createTravel(travelData);
        return ResponseEntity.ok(travelData);

    }

    @GetMapping("/travels/{id}")
    public ResponseEntity<?> getTravelById(@PathVariable Long id) {
        return ResponseEntity.ok(travelService.getTravel(id));
    }

    @GetMapping("/travels")
    public ResponseEntity<?> getAllTravels(){

        return ResponseEntity.ok(modelMapper.map(travelService.getTravels(), new TypeToken<List<TravelDto>>() {
        }.getType()));
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
        String message = "Travel deleted successfully: ";
        return ResponseEntity.ok(message + id);
    }


    @GetMapping("/travelsPages")
    public ResponseEntity<Page<TravelDto>> getAllTravelsPages(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam Sort.Direction sortDirection,
            @RequestParam String sortBy,
            @RequestParam String name,
            @RequestParam String destination,
            @RequestParam int duration,
            @RequestParam int travelers,
            @RequestParam String type
            ) {

        return new  ResponseEntity<>(travelService.getAllTravels(new PageProperties(pageNumber, pageSize, sortDirection, sortBy), new TravelSearchCriteria(name, destination, duration, travelers, type)), HttpStatus.OK);

    }

}
