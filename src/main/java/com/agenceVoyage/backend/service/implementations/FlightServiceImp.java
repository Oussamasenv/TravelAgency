package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.Facility;
import com.agenceVoyage.backend.model.Flight;
import com.agenceVoyage.backend.model.FlightAvailibility;
import com.agenceVoyage.backend.model.Reservation;
import com.agenceVoyage.backend.repository.FlightRepository;
import com.agenceVoyage.backend.service.interfaces.FlightService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FlightServiceImp implements FlightService {


    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight createFlight(Flight flight) {;
        return flightRepository.save(flight);
    }

    @Override
    public Flight setFlightToReserve(Flight flight, int placesToReserve) {


                int newPlacesLeft = flight.getPlacesLeft() - placesToReserve;
                flight.setPlacesLeft(newPlacesLeft);

                if(placesToReserve == flight.getPlacesLeft()) {
                    flight.setAvailability(FlightAvailibility.RESERVED);
                }

                return flightRepository.save(flight);

    }

    @Override
    public Flight getFlight(long id){
        return flightRepository.getReferenceById(id);
    }


}
