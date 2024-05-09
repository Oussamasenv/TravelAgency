package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.model.Room;
import com.agenceVoyage.backend.model.RoomAvailability;
import com.agenceVoyage.backend.repository.RoomRepository;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class RoomServiceImp implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RoomDto saveRoom(RoomDto roomDto) {
        roomRepository.save(modelMapper.map(roomDto, Room.class));;
        return roomDto;
    }


    public double setRoomsToReserve(ConcurrentLinkedQueue<RoomDto> roomDtos, int duration) {

        double facilitiesPricing = 0;

        for (RoomDto roomDto : roomDtos) {

                double roomPricing = roomDto.getPricePerNight() * duration;
                facilitiesPricing += roomPricing;
                roomDto.setAvailability(RoomAvailability.UNAVAILABLE);

        }
        roomRepository.saveAll(modelMapper.map(roomDtos, new TypeToken<List<Room>>() {
        }.getType()));;

        return facilitiesPricing;
    }

    public void setRoomsToCancelReservation(ConcurrentLinkedQueue<RoomDto> roomDtos) {

        for (RoomDto roomDto : roomDtos) {
            roomDto.setAvailability(RoomAvailability.AVAILABLE);
        }
        roomRepository.saveAll(modelMapper.map(roomDtos, new TypeToken<List<Room>>() {
        }.getType()));
    }

    @Override
    public ConcurrentLinkedQueue<Room> findAllByIds(List<Long> ids) {
        List<Room> roomList = roomRepository.findAllById(ids);
        return new ConcurrentLinkedQueue<>(roomList);

    }

    @Override
    public ConcurrentLinkedQueue<RoomDto> saveAll(ConcurrentLinkedQueue<RoomDto> roomDtos) {

//        return (List<RoomDto>) roomMapper.toRoomDtos(roomRepository.saveAll(roomMapper.toRooms(roomDtos)));
        ConcurrentLinkedQueue<Room> rooms = modelMapper.map(roomDtos, new TypeToken<ConcurrentLinkedQueue<Room>>() {}.getType());
        roomRepository.saveAll(rooms);

        return roomDtos;


    }
}
