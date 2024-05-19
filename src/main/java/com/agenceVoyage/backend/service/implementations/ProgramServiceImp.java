package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.model.Program;
import com.agenceVoyage.backend.repository.ProgramRepository;
import com.agenceVoyage.backend.service.interfaces.ProgramService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteProgram(long id) {
        programRepository.deleteById(id);
    }

    @Override
    public ProgramDto updateProgram(long id, ProgramDto programDto) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setName(programDto.getName());
            program.setDescription(programDto.getDescription());
            program.setDuration(programDto.getDuration());
            programRepository.save(program);

            return programDto;

        } else {
            throw new RuntimeException("Program not found");
        }
    }

    @Override
    public List<ProgramDto> getAllPrograms() {
        return modelMapper.map(programRepository.findAll(), new TypeToken<List<ProgramDto>>(){} .getType());
    }
}
