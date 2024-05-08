package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class HotelDto {

    private long id;

    @Size(min = 8, max = 50)
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @Min(0)
    private int starts;

    @NotNull
    @Min(0)
    private int roomsNumber;

    @NotNull
    private Collection<Room> rooms;

}
