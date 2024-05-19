package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.wrapper.HotelData;

import java.util.List;

public interface HotelService {

    public HotelDto createHotel(HotelDto hotelDto);

    public HotelDto createHotelWithRooms(HotelData hotelData);

    public void deleteHotel(long id);

    public HotelDto updateHotel(long id, HotelDto hotelDto);

    public List<HotelDto> getHotels();



}
