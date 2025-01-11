package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.dto.TravelerDto;
import com.agenceVoyage.backend.model.Reservation;
import com.agenceVoyage.backend.model.Traveler;
import jdk.dynalink.linker.LinkerServices;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface TravelerService {




    public TravelerDto save(TravelerDto travelerDto);

    public void setTravelersToReservation(ConcurrentLinkedQueue<TravelerDto> travelerDtos);

    public void deleteTraveler(long id);

    public List<TravelerDto> getTravelers();

    public TravelerDto updateTraveler(long id, TravelerDto travelerDto);

    public TravelerDto getTravelerById(long id);




}
