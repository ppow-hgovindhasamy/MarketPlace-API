package com.purchasingpower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by hgovindhasamy on 5/3/2017.
 */

@SpringBootApplication
@EnableZuulProxy
public class MarketPlaceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketPlaceApiApplication.class, args);
    }
}
