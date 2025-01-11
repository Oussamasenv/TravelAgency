package com.agenceVoyage.backend.criteriaRepositories.travelCq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelSearchCriteria {

//    private String type;
//    private String destination;
//    private ZonedDateTime departure;
    private String name;
    private String destination;
    private int duration;
    private int travelers;
    private String type;



}
