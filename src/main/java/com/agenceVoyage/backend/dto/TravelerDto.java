package com.agenceVoyage.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TravelerDto {

    private long id;

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String lastName;



    @NotNull
    private int age;


    @NotBlank
    private String identity;



}
