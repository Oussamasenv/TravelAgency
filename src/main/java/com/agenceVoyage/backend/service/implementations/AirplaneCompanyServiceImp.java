package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
import com.agenceVoyage.backend.model.AirplaneCompany;
import com.agenceVoyage.backend.repository.AirplaneCompanyRepository;
import com.agenceVoyage.backend.service.interfaces.AirplaneCompanyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<AirplaneCompanyDto> getAirplaneCompanyDtoList() {
        return modelMapper.map(airplaneCompanyRepository.findAll(), new TypeToken<List<AirplaneCompanyDto>>() {} .getType());
    }
    @Override
    public AirplaneCompanyDto updateAirplaneCompanyDto(long id, AirplaneCompanyDto planeCompanyDto) {

        Optional<AirplaneCompany> optionalAirplaneCompany = airplaneCompanyRepository.findById(id);
        if (optionalAirplaneCompany.isPresent()) {
            AirplaneCompany airplaneCompany = optionalAirplaneCompany.get();
            airplaneCompany.setName(planeCompanyDto.getName());
            airplaneCompanyRepository.save(airplaneCompany);

            return planeCompanyDto;

        } else {
            throw new RuntimeException("Airplane Company not found");
        }
    }

    @Override
    public void deleteAirplaneCompanyDto(long id) {

        airplaneCompanyRepository.deleteById(id);

    }
}
