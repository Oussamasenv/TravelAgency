package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.Reservation;
import com.agenceVoyage.backend.model.Traveler;
import jdk.dynalink.linker.LinkerServices;

import java.util.Collection;

public interface TravelerService {




    public Traveler save(Traveler traveler);

    public void setTravelersToReservation(Collection<Traveler> travelers);




}
