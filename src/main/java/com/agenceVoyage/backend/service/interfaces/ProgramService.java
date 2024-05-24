package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.criteriaRepositories.ProgramPage;
import com.agenceVoyage.backend.criteriaRepositories.ProgramSearchCriteria;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.model.Program;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProgramService {

    public ProgramDto saveProgram(ProgramDto programDto);

    public void deleteProgram(long id);

    public ProgramDto updateProgram(long id, ProgramDto programDto);

    public List<ProgramDto> getAllPrograms();

    public Page<Program> getPrograms(
            ProgramPage programPage,
            ProgramSearchCriteria programSearchCriteria);

}
