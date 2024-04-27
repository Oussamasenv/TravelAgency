package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.repository.HotelRepository;
import com.agenceVoyage.backend.service.interfaces.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImp implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}
