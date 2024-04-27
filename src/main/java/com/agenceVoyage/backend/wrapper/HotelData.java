package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.Room;
import lombok.*;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelData {

    private Hotel hotel;
    private ConcurrentLinkedDeque<Room> rooms;
}
