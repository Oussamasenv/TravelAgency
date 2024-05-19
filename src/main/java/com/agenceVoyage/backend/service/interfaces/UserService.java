package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.ReservationDto;
import com.agenceVoyage.backend.dto.UserDto;
import com.agenceVoyage.backend.model.Reservation;
import com.agenceVoyage.backend.model.User;
import com.agenceVoyage.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User saveUser(User user);

    public User setUserToReservation(User user, ReservationDto reservationDto);

    public User getUser(long id);

    public void deleteUser(long id);

    public UserDto updateUser(long id, UserDto userDto);

    public List<UserDto> getAllUsers();

    public UserDto addUser(UserDto userDto);

}
