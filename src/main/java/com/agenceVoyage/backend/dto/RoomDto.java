package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.RoomAvailability;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomAvailability availability;

    @NotNull
    @Min(0)
    private int roomNumber;

    @NotNull
    @DecimalMin(value = "0.0")
    private double pricePerNight;
    private Hotel hotel;
}
