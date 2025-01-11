package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    public Boolean existsFlightById(double id);

    public List<Travel> findTravelsByIdIs(List<Long> ids);


}
