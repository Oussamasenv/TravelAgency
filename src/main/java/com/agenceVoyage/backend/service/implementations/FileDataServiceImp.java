package com.agenceVoyage.backend.service.implementations;

import com.agenceVoyage.backend.model.Filedata;
import com.agenceVoyage.backend.repository.FileDataRepository;
import com.agenceVoyage.backend.service.interfaces.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileDataServiceImp implements FileDataService {

    @Autowired
    private FileDataRepository fileDataRepository;


    @Override
    public Filedata uploadImageToFIleSystem(MultipartFile file)  throws IOException {
        String FOLDER_PATH = "C:/Users/PcMac/Desktop/jwt+react/TravelAgency/src/main/java/com/agenceVoyage/backend/assets/";
        String filePath= FOLDER_PATH +file.getOriginalFilename();

        Filedata filedata = Filedata.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build();

        Filedata fileData = fileDataRepository.save(filedata);


        file.transferTo(new File(filePath));

        return fileData;
    }

    @Override
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Filedata> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }
}
