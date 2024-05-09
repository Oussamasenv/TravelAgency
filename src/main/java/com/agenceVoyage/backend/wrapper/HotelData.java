package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.Room;
import lombok.*;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelData {


    private HotelDto hotelDto;
    private ConcurrentLinkedQueue<RoomDto> roomDtos;
}
