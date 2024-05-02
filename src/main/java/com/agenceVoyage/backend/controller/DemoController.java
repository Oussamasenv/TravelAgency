package com.agenceVoyage.backend.controller;

import com.agenceVoyage.backend.model.User;
import com.agenceVoyage.backend.service.UserDetailsServiceImp;
import com.agenceVoyage.backend.service.implementations.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;


    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }

//    @GetMapping("/user/{username}")
//    public UserDetails finUser(@PathVariable String username){
//
//        Optional<User> optionalUser = userServiceImp.finduser(username);
//        if(optionalUser.isPresent()){
//            return optionalUser.get();
//        } else {
//            return null;
//        }
//    }




}
