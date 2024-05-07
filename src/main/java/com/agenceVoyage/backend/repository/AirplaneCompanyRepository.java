package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.AirplaneCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneCompanyRepository extends JpaRepository<AirplaneCompany, Long> {
}
