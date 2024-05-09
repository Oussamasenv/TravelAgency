package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
import com.agenceVoyage.backend.model.AirplaneCompany;
import com.agenceVoyage.backend.repository.AirplaneCompanyRepository;
import com.agenceVoyage.backend.service.interfaces.AirplaneCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneCompanyServiceImp implements AirplaneCompanyService {

    @Autowired
    private AirplaneCompanyRepository airplaneCompanyRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AirplaneCompanyDto saveAirplane(AirplaneCompanyDto airplaneCompanyDto) {

        return modelMapper.map(airplaneCompanyRepository.save(modelMapper.map(airplaneCompanyDto, AirplaneCompany.class)), AirplaneCompanyDto.class);

    }
}
