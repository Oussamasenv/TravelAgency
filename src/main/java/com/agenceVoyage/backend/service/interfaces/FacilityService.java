package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.Facility;
import com.agenceVoyage.backend.model.Flight;
import com.agenceVoyage.backend.model.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;


public interface FacilityService {

    public Facility createService(Facility facility);

    public double setFacilitiesToReserve(ConcurrentLinkedDeque<Facility> facilities, Flight flight);

    public List<Facility> findAllByIds(List<Long> ids);
}
