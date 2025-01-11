package com.agenceVoyage.backend.dto;


import com.agenceVoyage.backend.model.Filedata;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    private int stars;

    @NotNull
    @Min(0)
    private int roomsNumber;

    private Collection<RoomDto> rooms;

//    @NotNull
    private Collection<MultipartFile> files;

    private Collection<Filedata> filedatas;

}
