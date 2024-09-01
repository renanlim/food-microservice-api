//package com.cefet.ms_item.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import com.cefet.ms_item.model.RestaurantModel;
//
//@FeignClient(name = "ms-restaurant", url = "http://localhost:8081/restaurante")
//public interface RestaurantClient {
//
//    @GetMapping("/{idRestaurante}")
//    RestaurantModel getRestauranteById(@PathVariable("idRestaurante") String idRestaurante);
//}