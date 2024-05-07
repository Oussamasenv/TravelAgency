package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.Room;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public interface RoomService {
    public Room createRoom(Room room);

    public double setRoomsToReserve(ConcurrentLinkedDeque<Room> rooms, int duration);

    public void setRoomsToCancelReservation(ConcurrentLinkedDeque<Room> rooms);

    public List<Room> findAllByIds(List<Long> ids);
}
