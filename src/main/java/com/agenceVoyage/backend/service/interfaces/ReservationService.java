package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.dto.*;
import com.agenceVoyage.backend.model.*;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface ReservationService {
    public ReservationDto save(ReservationDto reservationDto);

    public ReservationDto setReservationToReserve(
            TravelDto travelDto,
            double roomsPricing,
            ConcurrentLinkedQueue<FacilityDto> facilityDtos,
            ConcurrentLinkedQueue<TravelerDto> travelerDtos,
            ConcurrentLinkedQueue<RoomDto> roomDtos,
            User user);

    public ReservationDto cancelReservation(long id);

    public ReservationDto getReservationById(long id);
}
