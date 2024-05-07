package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.repository.TravelRepository;
import com.agenceVoyage.backend.service.interfaces.AirplaneCompanyService;
import com.agenceVoyage.backend.service.interfaces.TravelService;
import com.agenceVoyage.backend.wrapper.TravelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;


@Service
public class TravelServiceImp implements TravelService {


    @Autowired
    private TravelRepository travelRepository;

    private final AirplaneCompanyService airplaneCompanyService;

    public TravelServiceImp(AirplaneCompanyServiceImp airplaneCompanyServiceImp) {
        this.airplaneCompanyService = airplaneCompanyServiceImp;

    }

    @Override
    public Travel saveTravel(Travel travel) {;
        return travelRepository.save(travel);
    }

    @Override
    public Travel setTravelToReserve(Travel travel, int placesToReserve) {


                int newPlacesLeft = travel.getPlacesLeft() - placesToReserve;
                travel.setPlacesLeft(newPlacesLeft);

                if(placesToReserve == travel.getPlacesLeft()) {
                    travel.setAvailability(FlightAvailibility.RESERVED);
                }

                return travelRepository.save(travel);

    }

    @Override
    public Travel setTravelToCancelReservation(Travel travel, int placesToCancel) {
        travel.setAvailability(FlightAvailibility.AVAILABLE);
        travel.setPlacesLeft(travel.getPlacesLeft() + placesToCancel);
        return travelRepository.save(travel);
    }


    @Override
    public Travel getTravel(long id){
        return travelRepository.getReferenceById(id);
    }

    @Override
    public TravelData createTravel(TravelData travelData) {

        Travel travel = travelData.getTravel();
//        AirplaneCompany airplaneCompany = travelData.getAirplaneCompany();

        ConcurrentLinkedDeque<Program> programs = travelData.getPrograms();

        int duration = 0;

        for(Program program : programs) {
            duration += program.getDuration();
        }


        ZonedDateTime returnDate = travel.getDeparture().plusDays(travel.getDuration());
        travel.setAvailability(FlightAvailibility.AVAILABLE);
        travel.setPlacesLeft(travel.getGroupSize());
        travel.setDuration(duration);
        travel.setReturnDate(returnDate);
        travel.setPrograms(travelData.getPrograms());
        travel.setAirplaneCompany(travelData.getAirplaneCompany());
        travelRepository.save(travel);


//        airplaneCompany.setTravels();
//        airplaneCompanyService.saveAirplane(airplane);

        return travelData;


    }

}
