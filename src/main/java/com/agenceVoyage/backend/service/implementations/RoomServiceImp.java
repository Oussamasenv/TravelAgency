package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.Room;
import com.agenceVoyage.backend.repository.RoomRepository;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImp implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
}
