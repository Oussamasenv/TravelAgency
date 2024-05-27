package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.criteriaRepositories.PageProperties;
import com.agenceVoyage.backend.criteriaRepositories.travelCq.TravelCq;
import com.agenceVoyage.backend.criteriaRepositories.travelCq.TravelSearchCriteria;
import com.agenceVoyage.backend.dto.AirplaneCompanyDto;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.repository.TravelRepository;
import com.agenceVoyage.backend.service.interfaces.AirplaneCompanyService;
import com.agenceVoyage.backend.service.interfaces.ProgramService;
import com.agenceVoyage.backend.service.interfaces.TravelService;
import com.agenceVoyage.backend.wrapper.TravelData;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    private final TravelCq travelCq;

    private final ProgramService programService;

    public TravelServiceImp(
            AirplaneCompanyServiceImp airplaneCompanyServiceImp,
            TravelCq travelCq,
            ProgramServiceImp programServiceImp) {
        this.airplaneCompanyService = airplaneCompanyServiceImp;
        this.travelCq = travelCq;
        this.programService = programServiceImp;

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

        int duration = 0;

        TravelDto travelDto = travelData.getTravelDto();
        ConcurrentLinkedQueue<AirplaneCompanyDto> airplaneCompanyDtos = travelData.getAirplaneCompanyDtos();

        // i need to get the program from database then assign it to travel
        ConcurrentLinkedQueue<ProgramDto> programDtos = travelData.getProgramDtos();

        for (ProgramDto programDto : travelData.getProgramDtos()) {
            duration += programDto.getDuration();
//            ProgramDto programDt = modelMapper.map(programService.getReferenceProgram(programDto.getId()), ProgramDto.class);
//            programDtos.add(programDt);
        }


        ZonedDateTime returnDate = travelDto.getDeparture().plusDays(travelDto.getDuration());
        travelDto.setAvailability(FlightAvailibility.AVAILABLE);
        travelDto.setPlacesLeft(travelDto.getGroupSize());
        travelDto.setDuration(duration);
        travelDto.setReturnDate(returnDate);
        travelDto.setPrograms(programDtos);
        travelDto.setAirplaneCompanies(airplaneCompanyDtos);
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
            travel.setAirplaneCompanies(modelMapper.map(travelDto.getAirplaneCompanies(), new TypeToken<List<AirplaneCompany>>() {
            }.getType()));
            travel.setDeparture(travelDto.getDeparture());
            travel.setDuration(travelDto.getDuration());
            travel.setReturnDate(travelDto.getReturnDate());
            travel.setGroupSize(travelDto.getGroupSize());
            travel.setPlacesLeft(travelDto.getPlacesLeft());
            travel.setAvailability(travelDto.getAvailability());
            travel.setPrograms(modelMapper.map(travelDto.getPrograms(), new TypeToken<ConcurrentLinkedQueue<Program>>(){} .getType() ));
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

    @Override
    public Page<Travel> getAllTravels(PageProperties pageProperties, TravelSearchCriteria travelSearchCriteria) {
        return travelCq.findAllWithFilter(pageProperties, travelSearchCriteria);
    }

}
