package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.repository.TravelRepository;
import com.agenceVoyage.backend.service.interfaces.AirplaneCompanyService;
import com.agenceVoyage.backend.service.interfaces.TravelService;
import com.agenceVoyage.backend.wrapper.TravelData;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.ZonedDateTime;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;


@Service
public class TravelServiceImp implements TravelService {

    @Autowired
    private TravelRepository travelRepository;

    private final AirplaneCompanyService airplaneCompanyService;
    @Autowired
    private ModelMapper modelMapper;

    public TravelServiceImp(AirplaneCompanyServiceImp airplaneCompanyServiceImp) {
        this.airplaneCompanyService = airplaneCompanyServiceImp;

    }

    @Override
    public TravelDto saveTravel(TravelDto travelDto) {

        travelRepository.save(modelMapper.map(travelDto, Travel.class));
        return travelDto;
    }

    @Override
    public TravelDto setTravelToReserve(TravelDto travelDto, int placesToReserve) {


                int newPlacesLeft = travelDto.getPlacesLeft() - placesToReserve;
                travelDto.setPlacesLeft(newPlacesLeft);

                if(placesToReserve == travelDto.getPlacesLeft()) {
                    travelDto.setAvailability(FlightAvailibility.RESERVED);
                }

                travelRepository.save(modelMapper.map(travelDto, Travel.class));
                return travelDto;

    }

    @Override
    public TravelDto setTravelToCancelReservation(TravelDto travelDto, int placesToCancel) {
        travelDto.setAvailability(FlightAvailibility.AVAILABLE);
        travelDto.setPlacesLeft(travelDto.getPlacesLeft() + placesToCancel);
        travelRepository.save(modelMapper.map(travelDto, Travel.class));
        return travelDto;
    }


    @Override
    public TravelDto getTravel(long id){

        return modelMapper.map(travelRepository.getReferenceById(id), TravelDto.class);
    }

    @Override
    public TravelData createTravel(TravelData travelData) {

        TravelDto travelDto = travelData.getTravelDto();
        AirplaneCompanyDto airplaneCompanyDto = travelData.getAirplaneCompanyDto();


        ConcurrentLinkedQueue<ProgramDto> programDtos = travelData.getProgramDtos();

        int duration = 0;

        for(ProgramDto programDto : programDtos) {
            duration += programDto.getDuration();
        }


        ZonedDateTime returnDate = travelDto.getDeparture().plusDays(travelDto.getDuration());
        travelDto.setAvailability(FlightAvailibility.AVAILABLE);
        travelDto.setPlacesLeft(travelDto.getGroupSize());
        travelDto.setDuration(duration);
        travelDto.setReturnDate(returnDate);
        travelDto.setProgramDtos(travelData.getProgramDtos());
        travelDto.setAirplaneCompanyDto(airplaneCompanyDto);
        travelRepository.save(modelMapper.map(travelDto, Travel.class));


        return travelData;


    }

    @Override
    public void deleteTravel(long id) {
        travelRepository.deleteById(id);
    }

    @Override
    public TravelDto updateTravel(long id, TravelDto travelDto) {

        Optional<Travel> optionalTravel = travelRepository.findById(id);

        if (optionalTravel.isPresent()) {
            Travel travel = optionalTravel.get();
            travel.setAirplaneCompany(modelMapper.map(travelDto.getAirplaneCompanyDto(), AirplaneCompany.class));
            travel.setDeparture(travelDto.getDeparture());
            travel.setDuration(travelDto.getDuration());
            travel.setReturnDate(travelDto.getReturnDate());
            travel.setGroupSize(travelDto.getGroupSize());
            travel.setPlacesLeft(travelDto.getPlacesLeft());
            travel.setAvailability(travelDto.getAvailability());
            travel.setPrograms(modelMapper.map(travelDto.getProgramDtos(), new TypeToken<ConcurrentLinkedQueue<Program>>(){} .getType() ));
            travelRepository.save(travel);

            return travelDto;

        } else {
            throw new RuntimeException("Travel not found");
        }

    }

    @Override
    public List<TravelDto> getTravels() {
        return modelMapper.map(travelRepository.findAll(), new TypeToken<List<TravelDto>>() {} .getType());
    }

}
