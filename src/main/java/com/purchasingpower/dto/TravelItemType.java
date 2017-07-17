package com.purchasingpower.dto;

/**
 * Created by hgovindhasamy on 7/11/2017.
 */
public enum TravelItemType {
    FLIGHT, CAR, HOTEL, CRUISE, RESORT;

    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
