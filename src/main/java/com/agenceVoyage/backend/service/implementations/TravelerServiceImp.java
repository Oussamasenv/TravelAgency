package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.TravelerDto;
import com.agenceVoyage.backend.model.Traveler;
import com.agenceVoyage.backend.repository.TravelerRepository;
import com.agenceVoyage.backend.service.interfaces.TravelerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TravelerServiceImp implements TravelerService {

    @Autowired
    private TravelerRepository travelerRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TravelerDto save(TravelerDto travelerDto) {

        travelerRepository.save(modelMapper.map(travelerDto, Traveler.class));

        return travelerDto;

    }

    @Override
    public void setTravelersToReservation(ConcurrentLinkedQueue<TravelerDto> travelerDtos) {
        travelerRepository.saveAll(modelMapper.map(travelerDtos, new TypeToken<List<Traveler>>() {
        }.getType()));
    }

    @Override
    public void deleteTraveler(long id) {
        travelerRepository.deleteById(id);
    }

    @Override
    public List<TravelerDto> getTravelers() {
        return modelMapper.map(travelerRepository.findAll(), new TypeToken<List<TravelerDto>>() {} .getType());
    }

    @Override
    public TravelerDto updateTraveler(long id, TravelerDto travelerDto) {

        Optional<Traveler> optionalTraveler = travelerRepository.findById(id);

        if (optionalTraveler.isPresent()) {
            Traveler traveler = optionalTraveler.get();
            traveler.setName(travelerDto.getName());
            traveler.setLastName(travelerDto.getLastName());
            traveler.setAge(travelerDto.getAge());
            traveler.setIdentity(travelerDto.getIdentity());
            travelerRepository.save(traveler);

            return travelerDto;

        } else {
            throw new RuntimeException("Traveler not found");
        }


    }


}
