package com.agenceVoyage.backend.controller.admin;


import com.agenceVoyage.backend.model.Filedata;
import com.agenceVoyage.backend.service.implementations.FileDataServiceImp;
import com.agenceVoyage.backend.service.interfaces.FileDataService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/admin")
@RestController
public class FileDataAdminController {

    private final FileDataService fileDataService;

    public FileDataAdminController(FileDataServiceImp fileDataServiceImp) {
        this.fileDataService = fileDataServiceImp;
    }


    @GetMapping("/getFileData/{fileName}")
    public byte[] getFileData(@PathVariable String fileName) throws IOException {
        return fileDataService.downloadImageFromFileSystem(fileName);
    }



    @PostMapping("/saveFileData")
    public Filedata saveFileData(
            @RequestAttribute MultipartFile file) throws IOException {
        return fileDataService.uploadImageToFIleSystem(file);
    }

}
