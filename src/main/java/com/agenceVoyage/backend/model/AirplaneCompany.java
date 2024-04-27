package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AirplaneCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    @Size(min = 2, max = 30)
    private String name;

    @ManyToOne
    private Flight flight;

}
