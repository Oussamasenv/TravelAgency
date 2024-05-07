package com.agenceVoyage.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.concurrent.ConcurrentLinkedDeque;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AirplaneCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Size(min = 2, max = 30)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "airplaneCompany")
    private ConcurrentLinkedDeque<Travel> travels;

}
