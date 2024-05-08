package com.agenceVoyage.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProgramDto {

    private long id;

    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private int duration;

}
