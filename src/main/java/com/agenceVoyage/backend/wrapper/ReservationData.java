package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.dto.TravelerDto;
import com.agenceVoyage.backend.model.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationData {


    private long travelId;
    private ArrayList<Long> roomsIds = new ArrayList<>();
    private List<Long> facilityIds = new ArrayList<>();
//        private ConcurrentLinkedQueue<RoomDto> roomDtos;
//    private ConcurrentLinkedQueue<FacilityDto> facilityDtos;
    private ConcurrentLinkedQueue<TravelerDto> travelerDtos;
    private long userId;

//    @NotNull
//    private TravelDto travelDto;
//    @NotNull
//    private ConcurrentLinkedQueue<RoomDto> roomDtos;
//
//    private ConcurrentLinkedQueue<FacilityDto> facilityDtos;
//
//    @NotNull
//    private ConcurrentLinkedQueue<TravelerDto> travelerDtos;

//    @NotNull
//    private long userId;
//
//    private Long travelId;
//    private List<Long> roomIds;
//
//    @NotNull
//    private ConcurrentLinkedQueue<TravelerDto> travelerDtos;



}
