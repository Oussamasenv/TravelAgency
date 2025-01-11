package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.criteriaRepositories.PageProperties;
import com.agenceVoyage.backend.criteriaRepositories.programCq.ProgramSearchCriteria;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.model.Program;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface ProgramService {

    public ProgramDto saveProgram(ProgramDto programDto);

    public void deleteProgram(long id);

    public ProgramDto updateProgram(long id, ProgramDto programDto);

    public List<ProgramDto> getAllPrograms();

    public ConcurrentLinkedQueue<ProgramDto> getProgramsByIds(List<Long> ids);

    public Page<Program> getPrograms(
            PageProperties pageProperties,
            ProgramSearchCriteria programSearchCriteria);


    public ProgramDto getReferenceProgram(long id);
}
