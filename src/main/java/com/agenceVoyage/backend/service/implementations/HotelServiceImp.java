package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.RoomAvailability;
import com.agenceVoyage.backend.repository.HotelRepository;
import com.agenceVoyage.backend.service.interfaces.HotelService;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import com.agenceVoyage.backend.wrapper.HotelData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class HotelServiceImp implements HotelService {


    @Autowired
    private HotelRepository hotelRepository;

    private final RoomService roomService;
    @Autowired
    private ModelMapper modelMapper;

    public HotelServiceImp(RoomServiceImp roomServiceImp) {
        this.roomService = roomServiceImp;
    }

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {

//        return hotelMapper.toHotelDto(hotelRepository.save(hotelMapper.toHotel(hotelDto)));
        return modelMapper.map(hotelRepository.save(modelMapper.map(hotelDto, Hotel.class)), HotelDto.class);
    }

    @Override
    public HotelDto createHotelWithRooms(HotelData hotelData) {

        HotelDto hotelDto = hotelData.getHotelDto();
        ConcurrentLinkedQueue<RoomDto> roomDtos = hotelData.getRoomDtos();

        for (RoomDto roomDto : roomDtos) {
            System.out.println(roomDto.getRoomNumber());
            System.out.println(roomDto.getPricePerNight());
            roomDto.setAvailability(RoomAvailability.UNAVAILABLE);
        }



        hotelDto.setRooms(roomDtos);

        Hotel savedHotel = hotelRepository.save(modelMapper.map(hotelDto, Hotel.class));
        HotelDto savedHotelDto = modelMapper.map(savedHotel, HotelDto.class);

        System.out.println(hotelDto.getLocation());


//        hotelRepository.save(hotelMapper.toHotel(hotelDto));

        for(RoomDto roomDto : roomDtos) {
            roomDto.setHotelDto(savedHotelDto);
        }

//        roomService.saveAll(roomDtos);

        roomService.saveAll(roomDtos);


        return savedHotelDto;
    }
}
