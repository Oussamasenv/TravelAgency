package com.agenceVoyage.backend.designpatterns.strategy;

public class DailyPricingStartegy implements ServicePricing{



    @Override
    public double calculatePricing(int duration, double basePricePerDay, int daysOfService) {

        return basePricePerDay * daysOfService;

    }
}
