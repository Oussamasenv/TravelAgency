package com.agenceVoyage.backend.designpatterns.strategy;

public class DailyPricingStartegy implements ServicePricing{


    public static double calculatePricing(int duration, double basePricePerDay, int daysOfService) {

        return basePricePerDay * daysOfService;

    }
}
