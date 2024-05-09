package com.agenceVoyage.backend.service.implementations;



import com.agenceVoyage.backend.dto.ReservationDto;
import com.agenceVoyage.backend.model.Reservation;
import com.agenceVoyage.backend.model.User;
import com.agenceVoyage.backend.repository.UserRepository;
import com.agenceVoyage.backend.service.interfaces.UserService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserServiceImp implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    public Optional<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User setUserToReservation(User user, ReservationDto reservationDto) {

        user.setReservation(modelMapper.map(reservationDto, Reservation.class));
        return userRepository.save(user);

    }

    @Override
    public User getUser(long id){
        return userRepository.getReferenceById(id);
    }
}
