package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface RoomService {
    public RoomDto saveRoom(RoomDto roomDto);

    public double setRoomsToReserve(ConcurrentLinkedQueue<RoomDto> roomDtos, int duration);

    public void setRoomsToCancelReservation(ConcurrentLinkedQueue<RoomDto> roomDtos);

    public ConcurrentLinkedQueue<Room> findAllByIds(List<Long> ids);

    public ConcurrentLinkedQueue<RoomDto> saveAll(ConcurrentLinkedQueue<RoomDto> roomDtos);

    public void deleteRoom(long id);

    public Optional<RoomDto> getRoom(long id);

    public RoomDto updateRoom(long id, RoomDto room);
}
