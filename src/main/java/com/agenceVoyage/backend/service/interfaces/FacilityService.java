package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.Facility;
import com.agenceVoyage.backend.model.Travel;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


public interface FacilityService {

    public FacilityDto createService(FacilityDto facilityDto);

    public List<FacilityDto> getAllFacilities();

    public double setFacilitiesToReserve(ConcurrentLinkedQueue<FacilityDto> facilityDtos, TravelDto travelDto);

    public void deleteFacility(long id);

    public FacilityDto updateFacility(long id, FacilityDto facilityDto);

}
