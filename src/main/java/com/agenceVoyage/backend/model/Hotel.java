package com.agenceVoyage.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 8, max = 50)
    @Column(unique = true)
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @Min(0)
    private int starts;

    @NotNull
    @Min(0)
    private int roomsNumber;

    @OneToMany(mappedBy = "hotel")
    private Collection<Room> rooms;

}
