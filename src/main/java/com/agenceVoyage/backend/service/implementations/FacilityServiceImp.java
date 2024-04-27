package com.agenceVoyage.backend.service.implementations;


import com.agenceVoyage.backend.model.Facility;
import com.agenceVoyage.backend.repository.FacilityRepository;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityServiceImp implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;


    @Override
    public Facility createService(Facility facility) {
        return facilityRepository.save(facility);
    }
}
