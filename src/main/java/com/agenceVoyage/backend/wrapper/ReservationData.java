package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.model.*;
import lombok.*;

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

    private Travel travel;
    private ConcurrentLinkedDeque<Room> rooms;
    private ConcurrentLinkedDeque<Facility> facilities;
    private ConcurrentLinkedDeque<Traveler> travelers;
    private long userId;

}
