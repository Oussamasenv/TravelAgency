package com.agenceVoyage.backend.service.implementations;



import com.agenceVoyage.backend.dto.ReservationDto;
import com.agenceVoyage.backend.dto.UserDto;
import com.agenceVoyage.backend.model.Reservation;
import com.agenceVoyage.backend.model.User;
import com.agenceVoyage.backend.repository.UserRepository;
import com.agenceVoyage.backend.service.interfaces.UserService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

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
    public User setUserToReservation(User user, ReservationDto reservationDto ) {

        ConcurrentLinkedQueue<ReservationDto> reservationDtos = new ConcurrentLinkedQueue<>();
        reservationDtos.add(reservationDto);

        Type type = new TypeToken<ConcurrentLinkedQueue<Reservation>>() {
        }.getType();

        user.setReservations(modelMapper.map(reservationDtos, type));
        return userRepository.save(user);

    }

    @Override
    public User getUser(long id){
        return userRepository.getReferenceById(id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            userRepository.save(user);
            return userDto;

        } else {
            throw new RuntimeException("User not found");
        }

    }

    @Override
    public List<UserDto> getAllUsers() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserDto>>() {} .getType());
    }

    @Override
    public UserDto addUser(UserDto userDto) {

        userRepository.save(modelMapper.map(userDto, User.class));
        return userDto;
    }

}
