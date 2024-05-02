package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.Flight;
import com.agenceVoyage.backend.model.Reservation;

import java.util.Optional;

public interface FlightService {

    public Flight createFlight(Flight flight);

    public Flight setFlightToReserve(Flight flight, int placesToReserve);

    public Flight getFlight(long id);;
}
