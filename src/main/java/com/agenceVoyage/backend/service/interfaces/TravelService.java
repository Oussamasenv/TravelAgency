package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.Travel;
import com.agenceVoyage.backend.wrapper.TravelData;

public interface TravelService {

    public TravelDto saveTravel(TravelDto travelDto);

    public TravelDto setTravelToReserve(TravelDto travelDto, int placesToReserve);

    public TravelDto setTravelToCancelReservation(TravelDto travel, int placesToCancel);

    public TravelDto getTravel(long id);;

    public TravelData createTravel(TravelData travelData);
}
