package com.purchasingpower.service.impl;

import com.google.gson.Gson;
import com.purchasingpower.dto.CartTransferDto;
import com.purchasingpower.dto.ReservationDto;
import com.purchasingpower.dto.TravelItem;
import com.purchasingpower.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.util.List;


/**
 * Created by hgovindhasamy on 5/3/2017.
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    private static Logger logger = LogManager.getLogger(ReservationService.class);

    @Value("${hybris.service.username}")
    private String username;

    @Value("${hybris.service.password}")
    private String password;

    @Value("${hybris.service.cartUrl}")
    private String cartUrl;

    private final RestTemplate restTemplate;

    public ReservationServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void updateCart(ReservationDto reservationDto) {
        CartTransferDto cartTransferDto = convertToCartTransferDto(reservationDto);
        Gson gson = new Gson();
        String request = gson.toJson(cartTransferDto);
        String authString = username + ":" + password;
        String authStringEnc = DatatypeConverter.printBase64Binary(authString.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + authStringEnc);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setCacheControl("no-cache");

        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        logger.debug("Sending cart transfer: " + request);
        ResponseEntity<String> response = restTemplate.exchange(cartUrl, HttpMethod.POST, entity, String.class);
        logger.info("Status code on calling the hybris API :" + response.getStatusCode());
        logger.info("Response body on calling the hybris API :" + response.getBody());
        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            logger.error("Unauthorized error on calling the hybris API :" + cartUrl);
        }

    }

    private CartTransferDto convertToCartTransferDto(ReservationDto reservationDto) {
        CartTransferDto cartTransferDto = new CartTransferDto();
        cartTransferDto.setReservationId(reservationDto.getReservationId());
        cartTransferDto.setCustomerId(reservationDto.getCustomerId());
        cartTransferDto.setClientId(reservationDto.getClientId());
        cartTransferDto.setSubTotal(reservationDto.getSubTotal());
        cartTransferDto.setTotalTaxes(reservationDto.getTotalTaxes());
        cartTransferDto.setTotalFees(reservationDto.getTotalFees());
        cartTransferDto.setTotalAirfareBookingFees(reservationDto.getTotalAirfareBookingFees());
        cartTransferDto.setInformationUrl(reservationDto.getInformationUrl());
        List<TravelItem> travelItemList = reservationDto.getTravelItems();
        StringBuilder builder = new StringBuilder("Includes: ");
        for (TravelItem travelItem: travelItemList){
            builder.append(travelItem.getType().toString());
            builder.append(", ");
        }
        cartTransferDto.setDescription(builder.substring(0, builder.length() - 2));
        return cartTransferDto;
    }


}
