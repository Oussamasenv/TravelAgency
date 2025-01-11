package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.AirplaneCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface AirplaneCompanyRepository extends JpaRepository<AirplaneCompany, Long> {

    public ConcurrentLinkedQueue<AirplaneCompany> getAirplaneCompaniesByIdIs(List<Long> ids);


}
