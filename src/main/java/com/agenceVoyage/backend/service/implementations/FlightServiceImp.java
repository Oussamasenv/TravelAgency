package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.Flight;
import com.agenceVoyage.backend.repository.FlightRepository;
import com.agenceVoyage.backend.service.interfaces.FlightService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FlightServiceImp implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}
