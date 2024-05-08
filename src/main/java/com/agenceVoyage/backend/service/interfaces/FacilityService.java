package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.model.Facility;
import com.agenceVoyage.backend.model.Travel;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;


public interface FacilityService {

    public FacilityDto createService(FacilityDto facilityDto);

    public double setFacilitiesToReserve(ConcurrentLinkedDeque<Facility> facilities, Travel travel);

    public List<Facility> findAllByIds(List<Long> ids);
}
