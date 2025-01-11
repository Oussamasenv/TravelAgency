package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.dto.HotelDto;
import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.model.Filedata;
import com.agenceVoyage.backend.model.Hotel;
import com.agenceVoyage.backend.model.Room;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelData {


    @NotNull
    private HotelDto hotelDto;

    @NotNull
    private List<MultipartFile> hotelFiles;
    @NotNull
    private List<MultipartFile> roomFiles;

    @NotNull
    private ConcurrentLinkedQueue<RoomDto> roomDtos;
}
