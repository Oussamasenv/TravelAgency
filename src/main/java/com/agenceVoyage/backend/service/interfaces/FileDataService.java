package com.agenceVoyage.backend.service.interfaces;

import com.agenceVoyage.backend.model.Filedata;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileDataService {

    public Filedata uploadImageToFIleSystem(MultipartFile file) throws IOException;

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException;
}
