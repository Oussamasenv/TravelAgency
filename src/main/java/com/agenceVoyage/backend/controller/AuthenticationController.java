package com.agenceVoyage.backend.controller;

import com.agenceVoyage.backend.dto.AuthLogin;
import com.agenceVoyage.backend.dto.AuthRegister;
import com.agenceVoyage.backend.service.AuthenticationService;
import com.agenceVoyage.backend.model.AuthenticationResponse;
import com.agenceVoyage.backend.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Validated
public class AuthenticationController {

    private final AuthenticationService authService;
    private final ModelMapper modelMapper;

    public AuthenticationController(
            AuthenticationService authService,
            ModelMapper modelMapper) {

        this.authService = authService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthRegister request
            ) {

        return ResponseEntity.ok(authService.register(modelMapper.map(request, User.class)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
             @RequestBody AuthLogin request
    ) {
        return ResponseEntity.ok(authService.authenticate(modelMapper.map(request, User.class)));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @GetMapping("/login")
    public RedirectView LoginPage(){
        return new RedirectView("http://localhost:3000/login");
    }
}
