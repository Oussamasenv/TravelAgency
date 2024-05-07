package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.Room;
import com.agenceVoyage.backend.model.RoomAvailability;
import com.agenceVoyage.backend.repository.HotelRepository;
import com.agenceVoyage.backend.service.interfaces.HotelService;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import com.agenceVoyage.backend.wrapper.HotelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class HotelServiceImp implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    private final RoomService roomService;

    public HotelServiceImp(RoomServiceImp roomServiceImp) {
        this.roomService = roomServiceImp;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public HotelData createHotelWithRooms(HotelData hotelData) {

        Hotel hotel = hotelData.getHotel();
        ConcurrentLinkedDeque<Room> rooms = hotelData.getRooms();
        hotel.setRooms(rooms);
        hotelRepository.save(hotel);

        for (Room room : rooms) {
            room.setAvailability(RoomAvailability.UNAVAILABLE);
            room.setHotel(hotel);
            roomService.createRoom(room);
        }

        return hotelData;
    }
}
