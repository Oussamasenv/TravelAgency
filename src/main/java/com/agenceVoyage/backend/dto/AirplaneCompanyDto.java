package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.Filedata;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Getter @Setter
public class AirplaneCompanyDto {

    private long id;


    @Size(min = 2, max = 30)
    @NotBlank
    private String name;

//    @NotNull
    private Collection<MultipartFile> files;

    private Collection<Filedata> filedatas;


}
