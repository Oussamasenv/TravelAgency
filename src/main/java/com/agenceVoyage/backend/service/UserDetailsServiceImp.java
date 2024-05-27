package com.agenceVoyage.backend.service;

import com.agenceVoyage.backend.model.Reservation;
import com.agenceVoyage.backend.model.User;
import com.agenceVoyage.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            System.out.println("no user found");
            throw new UsernameNotFoundException("no user found");
        }


    }

    public User loadByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            System.out.println("no user found");
            throw new UsernameNotFoundException("no user found");
        }
    }

    public Optional<User> findUser(String username) {
        return repository.findByUsername(username);
    }


    public User saveUser(User user) {
        return repository.save(user);
    }




    public User getUser(long id){
        return repository.getReferenceById(id);
    }








}
