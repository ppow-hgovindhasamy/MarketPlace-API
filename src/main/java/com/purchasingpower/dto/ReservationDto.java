package com.purchasingpower.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by hgovindhasamy on 5/3/2017.
 */
@Data
public class ReservationDto {
    private String reservationId;
    private String customerId;
    private String clientId;
    private Double subTotal;
    private Double total;
    private Double totalTaxes;
    private Double totalFees;
    private Double totalAirfareBookingFees;
    private String informationUrl;
    private List<TravelItem> travelItems;

}
