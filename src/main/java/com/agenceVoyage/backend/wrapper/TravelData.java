package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.dto.TravelDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


@Getter
@Setter
public class TravelData {


//    @NotNull
//    private TravelDto travelDto;
//    @NotNull
//    private ConcurrentLinkedQueue<ProgramDto> programDtos;
//    @NotNull
//    private ConcurrentLinkedQueue<AirplaneCompanyDto> airplaneCompanyDtos;
//
//    private ConcurrentLinkedQueue<RoomDto> roomDtos;

    @NotNull
    private long travelId;

    @NotNull
    private List<Long> programIds;

    @NotNull
    private List<Long> airplaneCompanyIds;

    @NotNull
    private List<Long> roomIds;

}
