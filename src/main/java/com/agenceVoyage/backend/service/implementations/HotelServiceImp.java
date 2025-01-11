package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.model.Filedata;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.Program;
import com.agenceVoyage.backend.model.RoomAvailability;
import com.agenceVoyage.backend.repository.HotelRepository;
import com.agenceVoyage.backend.service.interfaces.HotelService;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import com.agenceVoyage.backend.wrapper.HotelData;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class HotelServiceImp implements HotelService {


    @Autowired
    private HotelRepository hotelRepository;

    private final RoomService roomService;
    @Autowired
    private ModelMapper modelMapper;

    private final FileDataServiceImp fileDataService;

    public HotelServiceImp(
            RoomServiceImp roomServiceImp,
            FileDataServiceImp fileDataServiceImp) {
        this.roomService = roomServiceImp;
        this.fileDataService = fileDataServiceImp;
    }

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {



            if (!hotelDto.getFiles().isEmpty()) {
                try {


                    ConcurrentLinkedQueue<Filedata> filedatas = new ConcurrentLinkedQueue<>();

                    for (MultipartFile file : hotelDto.getFiles()) {

                        Filedata filedata = fileDataService.uploadImageToFIleSystem(file);
                        filedatas.add(filedata);

                    }

                    hotelDto.setFiledatas(filedatas);

                    return modelMapper.map(hotelRepository.save(modelMapper.map(hotelDto, Hotel.class)), HotelDto.class);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("no file");
            }


            return null;


        }



    @Override
    public HotelDto createHotelWithRooms(HotelData hotelData) {

        HotelDto hotelDto = hotelData.getHotelDto();
        ConcurrentLinkedQueue<RoomDto> roomDtos = hotelData.getRoomDtos();

        ConcurrentLinkedQueue<Filedata> filedatas = new ConcurrentLinkedQueue<>();

        for (MultipartFile file : hotelDto.getFiles()) {

            Filedata filedata = null;
            try {
                filedata = fileDataService.uploadImageToFIleSystem(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            filedatas.add(filedata);

        }

        hotelDto.setFiledatas(filedatas);
        for (RoomDto roomDto : roomDtos) {
            System.out.println(roomDto.getRoomNumber());
            System.out.println(roomDto.getPricePerNight());
            roomDto.setAvailability(RoomAvailability.UNAVAILABLE);
        }



        hotelDto.setRooms(roomDtos);

        Hotel savedHotel = hotelRepository.save(modelMapper.map(hotelDto, Hotel.class));
        HotelDto savedHotelDto = modelMapper.map(savedHotel, HotelDto.class);

        System.out.println(hotelDto.getLocation());

        for(RoomDto roomDto : roomDtos) {
            roomDto.setHotelDto(savedHotelDto);
        }
        roomService.saveAll(roomDtos);
        return savedHotelDto;
    }

    @Override
    public void deleteHotel(long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public HotelDto updateHotel(long id, HotelDto hotelDto) {

        Optional<Hotel> optionHotel = hotelRepository.findById(id);

        if(optionHotel.isPresent()) {
            Hotel hotel = optionHotel.get();
            hotel.setName(hotelDto.getName());
            hotel.setLocation(hotelDto.getLocation());
            hotel.setRoomsNumber(hotelDto.getRoomsNumber());
            hotel.setRooms(modelMapper.map(hotelDto.getRooms(), new TypeToken<ConcurrentLinkedQueue<RoomDto>>() {} .getType()));
            hotel.setLocation(hotelDto.getLocation());
            hotelRepository.save(hotel);

            return hotelDto;

        } else {
            throw new RuntimeException("Hotel does not exist");
        }



    }

    @Override
    public List<HotelDto> getHotels() {

        return modelMapper.map(hotelRepository.findAll(), new TypeToken<List<HotelDto>>() {} .getType());

    }
}
