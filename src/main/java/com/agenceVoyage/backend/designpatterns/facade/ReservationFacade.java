package com.agenceVoyage.backend.designpatterns.facade;

import com.agenceVoyage.backend.dto.*;
import com.agenceVoyage.backend.model.*;
import com.agenceVoyage.backend.service.implementations.*;
import com.agenceVoyage.backend.service.interfaces.*;
import com.agenceVoyage.backend.wrapper.ReservationData;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
public class ReservationFacade {


    private final FacilityService facilityService;

    private final RoomService roomService;

    private final UserService userService;

    private final ReservationService reservationService;

    private final TravelerService travelerService;

    private final TravelService travelService;


    //preferring constructor injection over field injection for best practices usage
    public ReservationFacade(
            RoomServiceImp roomServiceImp,
            ReservationServiceImp reservationServiceImp,
            TravelerServiceImp travelerServiceImp,
            FacilityServiceImp facilityServiceImp,
            UserServiceImp userDetailsServiceImp,
            TravelServiceImp travelServiceImp) {
        this.roomService = roomServiceImp;
        this.reservationService = reservationServiceImp;
        this.travelerService = travelerServiceImp;
        this.facilityService = facilityServiceImp;
        this.userService = userDetailsServiceImp;
        this.travelService = travelServiceImp;
    }


    public ReservationDto createReservation(ReservationData reservationData) {


        TravelDto travelDto = reservationData.getTravelDto();
        ConcurrentLinkedQueue<RoomDto> roomDtos = reservationData.getRoomDtos();
        ConcurrentLinkedQueue<TravelerDto> travelerDtos = reservationData.getTravelerDtos();
        ConcurrentLinkedQueue<FacilityDto> facilityDtos = reservationData.getFacilityDtos();
        User user = userService.getUser(reservationData.getUserId());



        // 1.Flight necessary logic for reservation
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

            TravelDto travelDto = reservationDto.getTravelDto();
            Collection<RoomDto> roomDtosVar = reservationDto.getRoomsDto();
            ConcurrentLinkedQueue<RoomDto> roomDtos = new ConcurrentLinkedQueue<RoomDto>(roomDtosVar);

            // 1. travel necessary logic for cancel reservation
            travelService.setTravelToCancelReservation(travelDto, reservationDto.getTravelerDtos().size());

            // 2. room necessary logic for cancel reservation
            roomService.setRoomsToCancelReservation(roomDtos);

            // 3. room necessary logic for cancel reservation
            reservationService.cancelReservation(id);

            return reservationDto;

        }



}
