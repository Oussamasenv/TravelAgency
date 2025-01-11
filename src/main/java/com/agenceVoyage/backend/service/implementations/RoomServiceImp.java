package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.dto.ReservationDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.repository.HotelRepository;
import com.agenceVoyage.backend.repository.RoomRepository;
import com.agenceVoyage.backend.service.interfaces.FileDataService;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class RoomServiceImp implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileDataService fileDataService;

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public RoomDto saveRoom(RoomDto roomDto) {

            if (!roomDto.getFiles().isEmpty()) {
                try {


                    ConcurrentLinkedQueue<Filedata> filedatas = new ConcurrentLinkedQueue<>();

                    for (MultipartFile file : roomDto.getFiles()) {

                        Filedata filedata = fileDataService.uploadImageToFIleSystem(file);
                        filedatas.add(filedata);

                    }

                    roomDto.setFiledatas(filedatas);
                    roomDto.setAvailability(RoomAvailability.AVAILABLE);

                    Hotel hotel = new Hotel();
                    hotel = hotelRepository.getReferenceById(roomDto.getHotelId());

                    roomDto.setHotelDto(modelMapper.map(hotel, HotelDto.class));

                    return modelMapper.map(roomRepository.save(modelMapper.map(roomDto, Room.class)), RoomDto.class);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("no file");
            }


            return null;


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

    @Override
    public void deleteRoom(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public RoomDto getRoom(long id) {

        return modelMapper.map(roomRepository.getReferenceById(id), RoomDto.class);
    }

    @Override
    public RoomDto updateRoom(long id, RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);

        if(optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            room.setAvailability(roomDto.getAvailability());
            room.setRoomNumber(roomDto.getRoomNumber());
            room.setPricePerNight(roomDto.getPricePerNight());
            room.setHotel(modelMapper.map(roomDto.getHotelDto(), Hotel.class));

            roomRepository.save(room);

            return roomDto;
        } else {
            throw new RuntimeException("Invalid room");
        }

    }

    @Override
    public ConcurrentLinkedQueue<RoomDto> getAllRooms() {
        return new ConcurrentLinkedQueue<>(
                modelMapper.map(roomRepository.findAll(), new TypeToken<List<RoomDto>>() {
                }.getType()));
    }

    @Override
    public ConcurrentLinkedQueue<RoomDto> getRoomsByIds(List<Long> ids) {

        ConcurrentLinkedQueue<Room> rooms = roomRepository.getRoomsByIdIs(ids);
        return modelMapper.map(rooms, new TypeToken<List<RoomDto>>() {} .getType());
    }
}
