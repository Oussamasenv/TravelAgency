package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.wrapper.HotelData;

public interface HotelService {
    public Hotel createHotel(Hotel hotel);

    public HotelData createHotelWithRooms(HotelData hotelData);
}
