package com.agenceVoyage.backend.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    private String name;
    private String description;
    private int duration;

    @ManyToMany(mappedBy = "programs")
    private Collection<Flight> flights;
}
