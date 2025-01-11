package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.*;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.repository.ReservationRepository;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import com.agenceVoyage.backend.service.interfaces.ReservationService;
import com.agenceVoyage.backend.service.interfaces.TravelService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service


public class ReservationServiceImp implements ReservationService {



    @Autowired
    private ReservationRepository reservationRepository;

    private TravelService travelService;

    private final FacilityService facilityService;
    @Autowired
    private ModelMapper modelMapper;


    public ReservationServiceImp(
            TravelServiceImp travelServiceImp,
            RoomServiceImp roomServiceImp,
            FacilityServiceImp facilityServiceImp
    ){
        this.facilityService = facilityServiceImp;
        this.travelService = travelServiceImp;
    }




    @Override
    public ReservationDto save(ReservationDto reservationDto) {

        return modelMapper.map(reservationRepository.save(modelMapper.map(reservationDto, Reservation.class)), ReservationDto.class);
    }

    @Override
    public ReservationDto setReservationToReserve(
            TravelDto travelDto,
            double roomsPricing,
            ConcurrentLinkedQueue<FacilityDto> facilityDtos,
            ConcurrentLinkedQueue<TravelerDto> travelerDtos,
            ConcurrentLinkedQueue<RoomDto> roomDtos,
            User user
            )

    {


        ReservationDto reservationDto = new ReservationDto();

        double totalPricing = travelDto.getInitialPrice();
        totalPricing += totalPricing * travelerDtos.size();
        totalPricing += facilityService.setFacilitiesToReserve(facilityDtos, travelDto);
        totalPricing += roomsPricing;

        reservationDto.setTotalPricing(totalPricing);
        reservationDto.setReservationDate(LocalDateTime.now());

        reservationDto.setRooms(roomDtos);
        reservationDto.setTravelers(travelerDtos);
        reservationDto.setFacilities(facilityDtos);
        reservationDto.setUser(user);
        reservationDto.setTravel(travelDto);
//        Travel travel = travelService.getTravel(travelDto.getId());
//        if (travel != null) {
//            travel.setPlacesLeft(travel.getPlacesLeft() - travelerDtos.size());
//            travelRepository.saveAndFlush(travel);
//        } else {
//            travelRepository.save(travelDto);
//        }
        reservationDto.setReservationStatus(ReservationStatus.RESERVATION_PASSED);

        return modelMapper.map(reservationRepository.saveAndFlush(modelMapper.map(reservationDto, Reservation.class)), ReservationDto.class);


    }

    @Override
    public ReservationDto cancelReservation(long id) {

        Reservation reservation = reservationRepository.getReferenceById(id);


        long daysUntilDeparture = ChronoUnit.DAYS.between(reservation.getTravel().getDeparture(), ZonedDateTime.now());

        if(daysUntilDeparture < 7){
            throw new RuntimeException("You can't cancel a reservation before 7 days of departure");
        }

        reservation.setReservationStatus(ReservationStatus.RESERVATION_CANCELED);
        reservation.setCancellationDate(LocalDateTime.now());
        reservationRepository.save(reservation);

        return modelMapper.map(reservationRepository.getReferenceById(id), ReservationDto.class);

    }

    @Override
    public ReservationDto getReservationById(long id) {

        return modelMapper.map(reservationRepository.getReferenceById(id), ReservationDto.class);

    }

    public List<ReservationDto> getAllReservations(){
        return modelMapper.map(reservationRepository.findAll(), new TypeToken<List<ReservationDto>>() {
        }.getType());
    }

}
