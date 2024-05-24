package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.criteriaRepositories.ProgramPage;
import com.agenceVoyage.backend.criteriaRepositories.ProgramSearchCriteria;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.model.Program;
import com.agenceVoyage.backend.service.implementations.ProgramServiceImp;
import com.agenceVoyage.backend.service.interfaces.ProgramService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class ProgramAdminController {


    private final ProgramService programService;

    public ProgramAdminController(ProgramServiceImp programServiceImp) {
        this.programService = programServiceImp;
    }

    @GetMapping("/programs")
    public ResponseEntity<?> getAllPrograms() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }


    @PostMapping(value = "/createProgram")
    @Transactional
    public ResponseEntity<?> saveProgram(
            @Valid @ModelAttribute ProgramDto programDto,
            BindingResult bindingResult
            ) {

        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));
        }


        return ResponseEntity.ok(programService.saveProgram(programDto));

    }


    @PutMapping("/programs/{id}")
    @Transactional
    public ResponseEntity<?> updateProgram(
            @PathVariable long id,
            @Valid @RequestBody ProgramDto programDto,
            BindingResult bindingResult){

        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));
        }

        programService.updateProgram(id, programDto);

        return ResponseEntity.ok(programDto);
    }



    @DeleteMapping("/programs/{id}")
    @Transactional
    public void deleteProgram(@PathVariable long id){

        programService.deleteProgram(id);
    }



    @GetMapping("/programsPages")
    public ResponseEntity<Page<Program>> getPograms(

            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam Sort.Direction sortDirection,
            @RequestParam String sortBy,
            @RequestParam String name,
            @RequestParam String description



            ) {


        return new ResponseEntity<>(programService.getPrograms(new ProgramPage(pageNumber, pageSize, sortDirection, sortBy), new ProgramSearchCriteria(name, description)), HttpStatus.OK);

    }




}
