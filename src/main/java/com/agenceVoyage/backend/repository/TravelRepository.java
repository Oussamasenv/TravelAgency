package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    public Boolean existsFlightById(double id);
}
