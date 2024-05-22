package com.agenceVoyage.backend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


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

    @OneToOne
    @NotNull
    private Filedata filedata;


    @NotNull
    private int duration;


}
