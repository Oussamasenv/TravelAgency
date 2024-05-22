package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.FacilityPricingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FacilityDto {

    private long id;

    @Size(min = 2, max = 30, message = "catching the error with bindingResult")
    @NotBlank
    private String name;

    @NotNull
    @DecimalMin(value = "0.0", message = "basePrice must be positive")
    private double basePricePerDay;

    @Enumerated(EnumType.STRING)
    private FacilityPricingType facilityPricingType;

}
