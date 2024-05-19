package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
import com.agenceVoyage.backend.service.implementations.AirplaneCompanyServiceImp;
import com.agenceVoyage.backend.service.interfaces.AirplaneCompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AirplaneCompnayAdminController {


    private final AirplaneCompanyService airplaneCompanyService;


    public AirplaneCompnayAdminController(AirplaneCompanyServiceImp airplaneCompanyServiceImp) {
        this.airplaneCompanyService = airplaneCompanyServiceImp;
    }




    @PostMapping("/createAirplaneCompany")
    @Transactional
    public ResponseEntity<?> saveAirplaneCompany(
            @Valid @RequestBody AirplaneCompanyDto airplaneCompanyDto,
            BindingResult bindingResult) {

        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));

        }

        return ResponseEntity.ok(airplaneCompanyService.saveAirplane(airplaneCompanyDto));
    }

    @GetMapping("/airplaneCompanies")
    public ResponseEntity<List<AirplaneCompanyDto>> getAllAirplaneCompanies(){

        return ResponseEntity.ok(airplaneCompanyService.getAirplaneCompanyDtoList());
    }


    @PutMapping("/airplaneCompanies/{id}")
    @Transactional
    public ResponseEntity<?> updateAirplaneCompany(
            @PathVariable long id,
            @RequestBody AirplaneCompanyDto airplaneCompanyDto,
            BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));
        }

        airplaneCompanyService.updateAirplaneCompanyDto(id, airplaneCompanyDto);

        return ResponseEntity.ok(airplaneCompanyDto);
    }

    @DeleteMapping("/airplaneCompanies/{id}")
    @Transactional
    public ResponseEntity<?> deleteAirplaneCompany(@PathVariable long id){
        airplaneCompanyService.deleteAirplaneCompanyDto(id);
        return ResponseEntity.ok("Airplane Company deleted successfully");
    }


}
