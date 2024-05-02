package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public Reservation findReservationByFlightId(double flight_id);


}
