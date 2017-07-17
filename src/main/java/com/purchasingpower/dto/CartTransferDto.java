package com.purchasingpower.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hgovindhasamy on 6/23/2017.
 */
@Data
@NoArgsConstructor
public class CartTransferDto {
    private String reservationId;
    private String customerId;
    private String clientId;
    private Double subTotal;
    private Double totalTaxes;
    private Double totalFees;
    private Double totalAirfareBookingFees;
    private String informationUrl;
    private String description;

}
