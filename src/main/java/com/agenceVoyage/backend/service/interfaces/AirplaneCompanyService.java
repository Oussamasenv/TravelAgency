package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;

import java.util.List;

public interface AirplaneCompanyService {

    public AirplaneCompanyDto saveAirplane(AirplaneCompanyDto airplaneCompanyDto);

    public List<AirplaneCompanyDto> getAirplaneCompanyDtoList();

    public AirplaneCompanyDto updateAirplaneCompanyDto(long id, AirplaneCompanyDto planeCompanyDto);

    public void deleteAirplaneCompanyDto(long id);

}
