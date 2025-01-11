package com.agenceVoyage.backend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
    private long id;

    @Size(min = 3, max = 50)
    @Column(unique = true)
    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String description;

    @NotBlank
    @Size(min = 3, max = 20)
    private String destination;

    @ManyToMany
//    @NotNull
    private Collection<Filedata> filedatas;


    @NotNull
    private int duration;


}
