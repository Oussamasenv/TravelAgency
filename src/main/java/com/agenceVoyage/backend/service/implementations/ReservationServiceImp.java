package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.*;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.repository.ReservationRepository;
import com.agenceVoyage.backend.service.interfaces.FacilityService;
import com.agenceVoyage.backend.service.interfaces.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service


public class ReservationServiceImp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    private final FacilityService facilityService;
    @Autowired
    private ModelMapper modelMapper;


    public ReservationServiceImp(
            RoomServiceImp roomServiceImp,
            FacilityServiceImp facilityServiceImp
    ){
        this.facilityService = facilityServiceImp;
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
        totalPricing += facilityService.setFacilitiesToReserve(facilityDtos, travelDto);
        totalPricing += roomsPricing;

        reservationDto.setTotalPricing(totalPricing);
        reservationDto.setReservationDate(LocalDateTime.now());

        reservationDto.setRoomsDto(roomDtos);
        reservationDto.setTravelerDtos(travelerDtos);
        reservationDto.setFacilitiesDto(facilityDtos);
        reservationDto.setUser(user);
        reservationDto.setTravelDto(travelDto);
        reservationDto.setReservationStatus(ReservationStatus.RESERVATION_PASSED);

        return modelMapper.map(reservationRepository.save(modelMapper.map(reservationDto, Reservation.class)), ReservationDto.class);


    }

    @Override
    public ReservationDto cancelReservation(long id) {

        Reservation reservation = reservationRepository.getReferenceById(id);

        reservation.setReservationStatus(ReservationStatus.RESERVATION_CANCELED);
        reservationRepository.save(reservation);

        return modelMapper.map(reservationRepository.getReferenceById(id), ReservationDto.class);
    }

    @Override
    public ReservationDto getReservationById(long id) {

        return modelMapper.map(reservationRepository.getReferenceById(id), ReservationDto.class);

    }


}
