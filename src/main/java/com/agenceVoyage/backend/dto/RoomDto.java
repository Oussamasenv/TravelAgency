package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.Filedata;
import com.agenceVoyage.backend.model.RoomAvailability;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Getter
@Setter
public class RoomDto {

    private long id;

    @Enumerated(EnumType.STRING)
    private RoomAvailability availability;

    @NotNull
    @Min(0)
    private int roomNumber;

    @NotNull
    @DecimalMin(value = "0.0")
    private double pricePerNight;

    @NotNull
    @Min(0)
    private long hotelId;

    private HotelDto hotelDto;

//    @NotNull
    private Collection<MultipartFile> files;

    private Collection<Filedata> filedatas;

}
