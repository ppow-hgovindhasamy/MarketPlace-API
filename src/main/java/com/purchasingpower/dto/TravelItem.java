package com.purchasingpower.dto;

/**
 * Created by hgovindhasamy on 7/11/2017.
 */
public class TravelItem {
    TravelItemType type;
    String description;

    public TravelItemType getType() {
        return type;
    }

    public void setType(TravelItemType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
