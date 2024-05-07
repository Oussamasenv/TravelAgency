package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public Reservation findReservationByTravelId(double flight_id);


}
