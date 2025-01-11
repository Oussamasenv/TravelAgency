package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.criteriaRepositories.PageProperties;
import com.agenceVoyage.backend.criteriaRepositories.travelCq.TravelSearchCriteria;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.Travel;
import com.agenceVoyage.backend.wrapper.TravelData;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TravelService {

    public TravelDto saveTravel(TravelDto travelDto);

    public TravelDto setTravelToReserve(TravelDto travelDto, int placesToReserve);

    public TravelDto setTravelToCancelReservation(TravelDto travel, int placesToCancel);

    public TravelDto getTravel(long id);;

    public TravelData createTravel(TravelData travelData);

    public void deleteTravel(long id);

    public TravelDto updateTravel(long id, TravelDto travelDto);

    public List<TravelDto> getTravels();

    public Page<TravelDto> getAllTravels(
            PageProperties pageProperties,
            TravelSearchCriteria travelSearchCriteria
    );



}
