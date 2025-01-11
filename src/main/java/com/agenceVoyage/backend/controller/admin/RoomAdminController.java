package com.agenceVoyage.backend.controller.admin;

import com.agenceVoyage.backend.dto.RoomDto;
import com.agenceVoyage.backend.service.implementations.RoomServiceImp;
import com.agenceVoyage.backend.service.interfaces.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class RoomAdminController {

    private final RoomService roomService;

    public RoomAdminController(RoomServiceImp roomServiceImp) {
        this.roomService = roomServiceImp;
    }

    @PostMapping("/createRoom")
    public ResponseEntity<?> createRoom(
            @ModelAttribute RoomDto roomDto) {

        return ResponseEntity.ok(roomService.saveRoom(roomDto));
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }



}
