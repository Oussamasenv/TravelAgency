package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.dto.TravelerDto;
import com.agenceVoyage.backend.model.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

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

    @NotNull
    private TravelDto travelDto;
    @NotNull
    private ConcurrentLinkedQueue<RoomDto> roomDtos;

    private ConcurrentLinkedQueue<FacilityDto> facilityDtos;

    @NotNull
    private ConcurrentLinkedQueue<TravelerDto> travelerDtos;

    @NotNull
    private long userId;

}
