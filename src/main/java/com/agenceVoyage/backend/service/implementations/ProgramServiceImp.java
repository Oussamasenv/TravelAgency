package com.agenceVoyage.backend.service.implementations;


import com.agenceVoyage.backend.criteriaRepositories.PageProperties;
import com.agenceVoyage.backend.criteriaRepositories.programCq.ProgramRepositoryCq;
import com.agenceVoyage.backend.criteriaRepositories.programCq.ProgramSearchCriteria;
import com.agenceVoyage.backend.dto.ProgramDto;
import com.agenceVoyage.backend.model.Filedata;
import com.agenceVoyage.backend.model.Program;
import com.agenceVoyage.backend.repository.ProgramRepository;
import com.agenceVoyage.backend.service.interfaces.FileDataService;
import com.agenceVoyage.backend.service.interfaces.ProgramService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImp implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileDataService fileDataService;

    private final ProgramRepositoryCq programRepositoryCq;




    public ProgramServiceImp(
            FileDataServiceImp fileDataServiceImp,
            ProgramRepositoryCq programRepositoryCq) {
        this.programRepositoryCq = programRepositoryCq;
        this.fileDataService = fileDataServiceImp;
    }

    @Override
    public ProgramDto saveProgram(ProgramDto programDto) {

        if (programDto.getFile() != null) {
            try {


                Filedata filedata = fileDataService.uploadImageToFIleSystem(programDto.getFile());
                System.out.println("filedata: " + filedata.getId());
                programDto.setFiledata(filedata);


                return modelMapper.map(programRepository.save(modelMapper.map(programDto, Program.class)), ProgramDto.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no file");
        }


        return null;


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


    public Page<Program> getPrograms(
            PageProperties pageProperties,
            ProgramSearchCriteria programSearchCriteria) {

        return programRepositoryCq.FindAllWithFilter(pageProperties, programSearchCriteria);


    }


}
