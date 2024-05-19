package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.service.implementations.HotelServiceImp;
import com.agenceVoyage.backend.service.interfaces.HotelService;
import com.agenceVoyage.backend.wrapper.HotelData;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class HotelAdminController {


    private final HotelService hotelService;


    public HotelAdminController(HotelServiceImp hotelServiceImp) {
        this.hotelService = hotelServiceImp;
    }



    @PostMapping("/createHotel")
    @Transactional
    public ResponseEntity<?> createHotel(
            @Valid @RequestBody HotelData hotelData,
            BindingResult bindingResult) {


        if (bindingResult.hasFieldErrors()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));

        }


        return ResponseEntity.ok(hotelService.createHotelWithRooms(hotelData));
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




