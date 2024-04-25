package com.agenceVoyage.backend.service;



import com.agenceVoyage.backend.model.User;
import com.agenceVoyage.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> finduser(String username) {
        return userRepository.findByUsername(username);
    }
}
