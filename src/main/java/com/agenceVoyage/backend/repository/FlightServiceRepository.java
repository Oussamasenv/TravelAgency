package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.FlightService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightServiceRepository extends JpaRepository<FlightService, Long> {
}
