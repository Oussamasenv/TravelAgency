package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.FacilityPricingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;




public class FacilityDto {

    private long id;

    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @DecimalMin(value = "0.0" )
    private double basePricePerDay;

    @Enumerated(EnumType.STRING)
    private FacilityPricingType facilityPricingType;

}
