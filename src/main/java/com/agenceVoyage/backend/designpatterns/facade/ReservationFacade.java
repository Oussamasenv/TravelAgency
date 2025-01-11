package com.agenceVoyage.backend.designpatterns.facade;

import com.agenceVoyage.backend.dto.*;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.service.implementations.*;
import com.agenceVoyage.backend.service.interfaces.*;
import com.agenceVoyage.backend.wrapper.ReservationData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
public class ReservationFacade {

    @PersistenceContext
    private EntityManager entityManager;

    private final FacilityService facilityService;

    private final RoomService roomService;

    private final UserService userService;

    private final ReservationService reservationService;

    private final TravelerService travelerService;

    private final TravelService travelService;
    private final ModelMapper modelMapper;
    private final LocalContainerEntityManagerFactoryBean entityManagerFactory;


    //preferring constructor injection over field injection for best practices usage
    public ReservationFacade(
            RoomServiceImp roomServiceImp,
            ReservationServiceImp reservationServiceImp,
            TravelerServiceImp travelerServiceImp,
            FacilityServiceImp facilityServiceImp,
            UserServiceImp userDetailsServiceImp,
            TravelServiceImp travelServiceImp, ModelMapper modelMapper, LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        this.roomService = roomServiceImp;
        this.reservationService = reservationServiceImp;
        this.travelerService = travelerServiceImp;
        this.facilityService = facilityServiceImp;
        this.userService = userDetailsServiceImp;
        this.travelService = travelServiceImp;
        this.modelMapper = modelMapper;
        this.entityManagerFactory = entityManagerFactory;
    }


    public ReservationDto createReservation(ReservationData reservationData) {


        TravelDto travelDto = travelService.getTravel(reservationData.getTravelId());

        ConcurrentLinkedQueue<RoomDto> roomDtos = new ConcurrentLinkedQueue<>();
        for (long id : reservationData.getRoomsIds()) {
            roomDtos.add(modelMapper.map(roomService.getRoom(id), RoomDto.class));
        }

        ConcurrentLinkedQueue<TravelerDto> travelerDtos = reservationData.getTravelerDtos();
        ConcurrentLinkedQueue<FacilityDto> facilityDtos = new ConcurrentLinkedQueue<>();

        for (long id : reservationData.getFacilityIds()){
            facilityDtos.add(modelMapper.map(facilityService.getFacilityById(id), FacilityDto.class));
        }

        User user = userService.getUser(reservationData.getUserId());
        ConcurrentLinkedQueue<ReservationDto> reservationDtos = new ConcurrentLinkedQueue<>();



        // 1.travel necessary logic for reservation
        travelService.setTravelToReserve(travelDto, travelerDtos.size());

        // 2. Room necessary logic for reservation
        double roomsPricing = roomService.setRoomsToReserve(roomDtos, travelDto.getDuration());

        // 3. Traveler necessary logic for reservation
        // save travelers in db;
        travelerService.setTravelersToReservation(travelerDtos);

        // 4. Facility necessary logic for reservation
        facilityService.setFacilitiesToReserve(facilityDtos, travelDto);

        // 5. Reservation necessary logic for reservation
        ReservationDto reservationDto = reservationService.setReservationToReserve(travelDto, roomsPricing, facilityDtos, travelerDtos, roomDtos, user);

        // 6. User necessary logic for reservation
        userService.setUserToReservation(user, reservationDto);

        return reservationDto;

        }

        public ReservationDto CancelReservation(long id) {

            ReservationDto reservationDto = reservationService.getReservationById(id);

            TravelDto travelDto = reservationDto.getTravel();
            Collection<RoomDto> roomDtosVar = reservationDto.getRooms();
            ConcurrentLinkedQueue<RoomDto> roomDtos = new ConcurrentLinkedQueue<RoomDto>(roomDtosVar);

            // 1. travel necessary logic for cancel reservation
            travelService.setTravelToCancelReservation(travelDto, reservationDto.getTravelers().size());

            // 2. room necessary logic for cancel reservation
            roomService.setRoomsToCancelReservation(roomDtos);

            // 3. room necessary logic for cancel reservation
            reservationService.cancelReservation(id);

            return reservationDto;

        }



}
