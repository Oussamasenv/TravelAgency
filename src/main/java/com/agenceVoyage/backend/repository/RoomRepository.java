package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Boolean existsById(double id);

 }
