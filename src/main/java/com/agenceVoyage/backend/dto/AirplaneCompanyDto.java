package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.Travel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentLinkedDeque;


@Getter @Setter
public class AirplaneCompanyDto {

    private long id;


    @Size(min = 2, max = 30)
    private String name;


    private ConcurrentLinkedDeque<Travel> travels;

}
