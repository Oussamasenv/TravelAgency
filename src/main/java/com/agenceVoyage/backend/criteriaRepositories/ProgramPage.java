package com.agenceVoyage.backend.criteriaRepositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProgramPage {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "id";


}
