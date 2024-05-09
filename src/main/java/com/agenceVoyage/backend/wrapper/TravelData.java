package com.agenceVoyage.backend.wrapper;

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


    private Travel travel;
    private ConcurrentLinkedQueue<Program> programs;
    private AirplaneCompany airplaneCompany;

}
