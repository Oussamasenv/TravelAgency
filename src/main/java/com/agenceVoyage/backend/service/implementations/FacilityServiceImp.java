package com.agenceVoyage.backend.service.implementations;


import com.agenceVoyage.backend.designpatterns.strategy.DailyPricingStartegy;
import com.agenceVoyage.backend.designpatterns.strategy.DurationPricingStrategy;
import com.agenceVoyage.backend.designpatterns.strategy.ServicePricing;
import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.mapper.FacilityMapper;
import com.agenceVoyage.backend.model.Facility;
import com.agenceVoyage.backend.model.FacilityPricingType;
import com.agenceVoyage.backend.model.Travel;
import com.agenceVoyage.backend.repository.FacilityRepository;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class FacilityServiceImp implements FacilityService {

    @Autowired
    private FacilityMapper facilityMapper;

    @Autowired
    private FacilityRepository facilityRepository;


    @Override
    public FacilityDto createService(FacilityDto facilityDto) {

        return facilityMapper.facilityToFacilityDto(facilityRepository.save(facilityMapper.facilityDtoToFacility(facilityDto)));
    }

    @Override
    public double setFacilitiesToReserve(ConcurrentLinkedDeque<Facility> facilities, Travel travel) {

        ServicePricing servicePricing;
        
        double flightPricing = 0;

        for (Facility facility : facilities){
            if(facility.getFacilityPricingType() == FacilityPricingType.LONG_DURATION){
                flightPricing += DurationPricingStrategy.calculatePricing(travel.getDuration(), facility.getBasePricePerDay(), travel.getFacilityDays());
            } else if (facility.getFacilityPricingType() == FacilityPricingType.SPECIFIC_DURATION){
                flightPricing += DailyPricingStartegy.calculatePricing(travel.getDuration(), facility.getBasePricePerDay(), travel.getFacilityDays());
            }

        }

        return flightPricing;

    }

    @Override
    public List<Facility> findAllByIds(List<Long> ids) {
        return facilityRepository.findAllById(ids);
    }


}
