package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.advice.ApplicationExceptionHandler;
import com.agenceVoyage.backend.dto.UserDto;
import com.agenceVoyage.backend.service.implementations.UserServiceImp;
import com.agenceVoyage.backend.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserServiceImp userServiceImp) {
        this.userService = userServiceImp;
    }


    @GetMapping("/users")
    public List<UserDto> getAllUsers(){

        return userService.getAllUsers();

    }

    @PostMapping("/addUser")
    @Transactional
    public ResponseEntity<?> addUser(
            @Valid @RequestBody UserDto userDto,
            BindingResult bindingResult){

        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));
        }

        return ResponseEntity.ok(userService.addUser(userDto));
    }



    @PutMapping("/users/{id}")
    @Transactional
    public ResponseEntity<?> updateUser(
            @PathVariable long id,
            @RequestBody UserDto userDto,
            BindingResult bindingResult) {

        if(bindingResult.hasFieldErrors()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationExceptionHandler.dtoErrorhandler(bindingResult));
        }

        userService.updateUser(id, userDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/users/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        
        userService.deleteUser(id);
        return ResponseEntity.ok("successfull delete");

    }


}
