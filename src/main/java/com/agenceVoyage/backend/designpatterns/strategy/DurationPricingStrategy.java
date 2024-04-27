package com.agenceVoyage.backend.designpatterns.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DurationPricingStrategy implements ServicePricing{

    private double discountRate;

    @Override
    public double calculatePricing(int duration, double basePricePerDay, int daysOfService) {

        if( daysOfService > 8 && daysOfService <20 ){
            discountRate = 0.1;
        } else if (daysOfService > 20 && daysOfService <40 ){
            discountRate = 0.2;
        } else {
            discountRate = 0.3;
        }

        double basePrice = duration * basePricePerDay;

        return (basePrice - (basePrice * discountRate));

    }
}
