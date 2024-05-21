package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    @Size(min = 2, max = 50)
    @NotBlank
    private String lastName;

    @NotNull
    private int age;

    @NotBlank
    private String identity;

}
