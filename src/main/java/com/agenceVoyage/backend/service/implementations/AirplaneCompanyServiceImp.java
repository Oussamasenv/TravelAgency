package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.AirplaneCompany;
import com.agenceVoyage.backend.repository.AirplaneCompanyRepository;
import com.agenceVoyage.backend.service.interfaces.AirplaneCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneCompanyServiceImp implements AirplaneCompanyService {

    @Autowired
    private AirplaneCompanyRepository airplaneCompanyRepository;

    @Override
    public AirplaneCompany saveAirplane(AirplaneCompany airplaneCompany) {
        return airplaneCompanyRepository.save(airplaneCompany);
    }
}
