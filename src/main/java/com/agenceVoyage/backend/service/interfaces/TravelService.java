package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.Travel;
import com.agenceVoyage.backend.wrapper.TravelData;

public interface TravelService {

    public Travel saveTravel(Travel travel);

    public Travel setTravelToReserve(Travel travel, int placesToReserve);

    public Travel setTravelToCancelReservation(Travel travel, int placesToCancel);

    public Travel getTravel(long id);;

    public TravelData createTravel(TravelData travelData);
}
