package com.agenceVoyage.backend.dto;


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
    @NotBlank
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
    private Collection<RoomDto> rooms;

}
