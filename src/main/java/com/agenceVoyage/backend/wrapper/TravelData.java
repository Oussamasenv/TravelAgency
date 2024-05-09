package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.AirplaneCompany;
import com.agenceVoyage.backend.model.Program;
import com.agenceVoyage.backend.model.Travel;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


@Getter
@Setter
public class TravelData {


    private TravelDto travelDto;
    private ConcurrentLinkedQueue<ProgramDto> programDtos;
    private AirplaneCompanyDto airplaneCompanyDto;

}
