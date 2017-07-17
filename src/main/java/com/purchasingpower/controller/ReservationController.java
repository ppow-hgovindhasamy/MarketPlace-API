package com.purchasingpower.controller;

import com.purchasingpower.dto.ReservationDto;
import com.purchasingpower.service.ReservationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by hgovindhasamy on 5/3/2017.
 */
@RestController
public class ReservationController {

    @Resource
    private ReservationService reservationService;

    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    public void setCartTransfer(@Valid @RequestBody ReservationDto reservationDto) {
        reservationService.updateCart(reservationDto);

    }
}
