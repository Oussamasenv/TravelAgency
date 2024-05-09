package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.model.Program;
import com.agenceVoyage.backend.repository.ProgramRepository;
import com.agenceVoyage.backend.service.interfaces.ProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImp implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProgramDto saveProgram(ProgramDto programDto) {

        return modelMapper.map(programRepository.save(modelMapper.map(programDto, Program.class)), ProgramDto.class);


    }
}
