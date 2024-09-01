package com.cefet.ms_order.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-restaurant")
public interface RestaurantClient {

    @GetMapping("/restaurante/{idRestaurant}")
    boolean existsById(@PathVariable("idRestaurant") String idRestaurant);
}
