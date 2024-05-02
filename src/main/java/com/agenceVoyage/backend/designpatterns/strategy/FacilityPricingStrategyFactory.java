package com.agenceVoyage.backend.designpatterns.strategy;

public class FacilityPricingStrategyFactory {

    public static ServicePricing createDurationServicePricing(){
        return new DurationPricingStrategy();
    }

    public static ServicePricing createDailyServicePricing(){
        return new DailyPricingStartegy();
    }
}
