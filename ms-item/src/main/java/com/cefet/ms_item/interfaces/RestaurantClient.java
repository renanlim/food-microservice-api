package com.cefet.ms_item.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-restaurant")
public interface RestaurantClient {

    @GetMapping("/restaurante/{idRestaurant}")
    String getRestaurantById(@PathVariable("idRestaurant") String idRestaurant);
}
