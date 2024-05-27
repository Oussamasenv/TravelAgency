package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.Filedata;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;


@Getter
@Setter
public class ProgramDto {

    private long id;

    @Size(min = 3, max = 50)
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private int duration;

    @NotBlank
    private String destination;

    @NotNull
    private Collection<MultipartFile> files;


    private Collection<Filedata> filedatas;


}
