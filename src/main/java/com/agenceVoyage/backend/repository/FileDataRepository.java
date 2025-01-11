package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.Filedata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FileDataRepository extends JpaRepository<Filedata, Long> {

    Optional<Filedata> findByName(String name);

}
