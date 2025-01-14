package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface AirplaneCompanyService {

    public AirplaneCompanyDto saveAirplane(AirplaneCompanyDto airplaneCompanyDto);

    public List<AirplaneCompanyDto> getAirplaneCompanyDtoList();

    public AirplaneCompanyDto updateAirplaneCompanyDto(long id, AirplaneCompanyDto planeCompanyDto);

    public void deleteAirplaneCompanyDto(long id);

    public ConcurrentLinkedQueue<AirplaneCompanyDto> getAirplaneCompaniesDtoByIds(List<Long> ids);

}
