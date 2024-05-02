package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.model.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationData {
//    private long flightId;
//    private ArrayList<Long> roomsIds = new ArrayList<>();
//    private HashMap<String, ArrayList<Long>> facilitiesIdsAndPricingType;
//    private ConcurrentLinkedDeque<Traveler> travelers;
//    private long userId;

    private Flight flight;
    private ConcurrentLinkedDeque<Room> rooms;
    private ConcurrentLinkedDeque<Facility> facilities;
    private ConcurrentLinkedDeque<Traveler> travelers;
    private long userId;

}
