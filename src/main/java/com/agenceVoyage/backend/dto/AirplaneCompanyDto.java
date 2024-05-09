package com.agenceVoyage.backend.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter @Setter
public class AirplaneCompanyDto {

    private long id;


    @Size(min = 2, max = 30)
    private String name;


}
