package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.Room;
import com.agenceVoyage.backend.model.RoomAvailability;
import com.agenceVoyage.backend.repository.RoomRepository;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class RoomServiceImp implements RoomService {

    @Autowired
    private RoomRepository roomRepository;


    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }


    public double setRoomsToReserve(ConcurrentLinkedDeque<Room> rooms, int duration) {

        double facilitiesPricing = 0;

        for (Room room : rooms) {

                double roomPricing = room.getPricePerNight() * duration;
                facilitiesPricing += roomPricing;
                room.setAvailability(RoomAvailability.UNAVAILABLE);

        }
        roomRepository.saveAll(rooms);

        return facilitiesPricing;
    }

    public void setRoomsToCancelReservation(ConcurrentLinkedDeque<Room> rooms) {

        for (Room room : rooms) {
            room.setAvailability(RoomAvailability.AVAILABLE);
        }
        roomRepository.saveAll(rooms);
    }

    @Override
    public List<Room> findAllByIds(List<Long> ids) {
        return roomRepository.findAllById(ids);
    }
}
