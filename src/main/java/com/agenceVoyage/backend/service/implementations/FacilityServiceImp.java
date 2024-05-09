package com.agenceVoyage.backend.service.implementations;


import com.agenceVoyage.backend.designpatterns.strategy.DailyPricingStartegy;
import com.agenceVoyage.backend.designpatterns.strategy.DurationPricingStrategy;
import com.agenceVoyage.backend.designpatterns.strategy.ServicePricing;
import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.Facility;
import com.agenceVoyage.backend.model.FacilityPricingType;
import com.agenceVoyage.backend.repository.FacilityRepository;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class FacilityServiceImp implements FacilityService {


    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public FacilityDto createService(FacilityDto facilityDto) {

//        return facilityMapper.toFacilityDto(facilityRepository.save(facilityMapper.toFacility(facilityDto)));
        facilityRepository.save(modelMapper.map(facilityDto, Facility.class));
        return facilityDto;

    }

    @Override
    public double setFacilitiesToReserve(ConcurrentLinkedQueue<FacilityDto> facilityDtos, TravelDto travelDto) {

        ServicePricing servicePricing;
        
        double flightPricing = 0;

        for (FacilityDto facilityDto : facilityDtos){
            if(facilityDto.getFacilityPricingType() == FacilityPricingType.LONG_DURATION){
                flightPricing += DurationPricingStrategy.calculatePricing(travelDto.getDuration(), facilityDto.getBasePricePerDay(), travelDto.getFacilityDays());
            } else if (facilityDto.getFacilityPricingType() == FacilityPricingType.SPECIFIC_DURATION){
                flightPricing += DailyPricingStartegy.calculatePricing(travelDto.getDuration(), facilityDto.getBasePricePerDay(), travelDto.getFacilityDays());
            }

        }

        return flightPricing;

    }



}
