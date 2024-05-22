package com.agenceVoyage.backend.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filedata")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Filedata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String type;

    private String filePath;
}
