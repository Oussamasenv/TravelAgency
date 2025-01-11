package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.model.Filedata;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.Room;
import com.agenceVoyage.backend.service.implementations.HotelServiceImp;
import com.agenceVoyage.backend.service.interfaces.HotelService;
import com.agenceVoyage.backend.wrapper.HotelData;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


@RestController
@RequestMapping("/admin")
public class HotelAdminController {


    private final HotelService hotelService;
    private final ModelMapper modelMapper;


    public HotelAdminController(HotelServiceImp hotelServiceImp, ModelMapper modelMapper) {
        this.hotelService = hotelServiceImp;
        this.modelMapper = modelMapper;
    }



    @PostMapping(value = "/createHotel")
    @Transactional
    public ResponseEntity<?> createHotel(
            @Valid @ModelAttribute HotelDto hotelDto,
            BindingResult bindingResult) {


        if (bindingResult.hasFieldErrors()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));

        }
        return ResponseEntity.ok(hotelService.createHotel(hotelDto));
    }

    @GetMapping("/hotels")
    @Transactional
    public ResponseEntity<?> getHotels(){
        return ResponseEntity.ok(hotelService.getHotels());
    }

    @PutMapping("/hotels/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable long id, @RequestBody HotelDto hotelDto){

        hotelService.updateHotel(id, hotelDto);
        return ResponseEntity.ok(hotelDto);
    }

    @DeleteMapping("/hotels/{id}")
    @Transactional
    public ResponseEntity<?> deleteHotel(@PathVariable long id){
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("hotel successfully deleted");
    }
}




