package com.agenceVoyage.backend.service.implementations;


import com.agenceVoyage.backend.model.FlightService;
import com.agenceVoyage.backend.repository.FlightServiceRepository;
import com.agenceVoyage.backend.service.interfaces.FlightServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceServiceImp implements FlightServiceService {

    @Autowired
    private FlightServiceRepository flightServiceRepository;


    @Override
    public FlightService createService(FlightService flightService) {
        return flightServiceRepository.save(flightService);
    }
}
