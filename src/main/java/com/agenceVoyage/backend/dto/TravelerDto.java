package com.agenceVoyage.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TravelerDto {

    private long id;

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    private int age;



}
