package com.agenceVoyage.backend.criteriaRepositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramSearchCriteria {
    private String name;
    private String description;
}
