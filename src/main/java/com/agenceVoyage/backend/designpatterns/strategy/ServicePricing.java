package com.agenceVoyage.backend.designpatterns.strategy;

public interface ServicePricing {
    public double calculatePricing(int duration, double basePricePerDay, int daysOfService);
}
