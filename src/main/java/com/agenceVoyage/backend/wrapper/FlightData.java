package com.agenceVoyage.backend.wrapper;

import com.agenceVoyage.backend.model.Facility;
import com.agenceVoyage.backend.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightData {
    private Flight flight;
    private ConcurrentLinkedDeque<Facility> facilities;
}


