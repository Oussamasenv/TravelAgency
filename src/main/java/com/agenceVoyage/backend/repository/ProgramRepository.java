package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    public ConcurrentLinkedQueue<Program> getProgramsByIdIs(List<Long> ids);

}
