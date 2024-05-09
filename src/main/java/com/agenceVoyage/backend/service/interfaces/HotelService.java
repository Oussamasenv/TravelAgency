package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.wrapper.HotelData;

public interface HotelService {

    public HotelDto createHotel(HotelDto hotelDto);
    public HotelDto createHotelWithRooms(HotelData hotelData);
}
