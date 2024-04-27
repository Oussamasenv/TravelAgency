package com.agenceVoyage.backend.designpatterns.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class DurationPricingStrategy implements ServicePricing{

    public static double calculatePricing(int duration, double basePricePerDay, double daysOfService) {

        double discountRate;

        if( duration >= 8 && duration <20 ){
            discountRate = 0.1;
        } else if (duration >= 20 && duration <40 ){
            discountRate = 0.2;
        } else if (duration >= 40 ){
            discountRate = 0.3;
        }else {
            discountRate =  0;
        }

        double basePrice = duration * basePricePerDay;

        return (basePrice - (basePrice * discountRate));

    }
}
