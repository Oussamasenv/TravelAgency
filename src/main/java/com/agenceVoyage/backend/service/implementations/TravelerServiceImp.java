package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.Traveler;
import com.agenceVoyage.backend.repository.TravelerRepository;
import com.agenceVoyage.backend.service.interfaces.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TravelerServiceImp implements TravelerService {

    @Autowired
    private TravelerRepository travelerRepository;

    @Override
    public Traveler save(Traveler traveler) {
        return travelerRepository.save(traveler);
    }

    @Override
    public void setTravelersToReservation(Collection<Traveler> travelers) {
        travelerRepository.saveAll(travelers);
    }
}
