package com.cefet.ms_restaurant.controller;

import com.cefet.ms_restaurant.model.RestaurantModel;
import com.cefet.ms_restaurant.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurante")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantModel> registerRestaurant(@RequestBody RestaurantModel restaurant) {
        RestaurantModel newRestaurant = restaurantService.registerRestaurant(restaurant);
        return ResponseEntity.ok(newRestaurant);
    }

    @PatchMapping("/{idRestaurant}")
    public ResponseEntity<RestaurantModel> updateRestaurant(@PathVariable String idRestaurant, @RequestBody RestaurantModel restaurantUpdated) {
        RestaurantModel restaurant = restaurantService.updateRestaurant(idRestaurant, restaurantUpdated, true);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{idRestaurant}")
    public ResponseEntity<RestaurantModel> updateRestaurantComplete(@PathVariable String idRestaurant, @RequestBody RestaurantModel restaurantUpdated) {
        RestaurantModel restaurant = restaurantService.updateRestaurant(idRestaurant, restaurantUpdated, false);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idRestaurant}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String idRestaurant) {
        try {
            restaurantService.deleteRestaurant(idRestaurant);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RestaurantModel>> listRestaurants() {
        List<RestaurantModel> restaurants = restaurantService.listRestaurant();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/nome/{name}")
    public ResponseEntity<List<RestaurantModel>> listRestaurantsByName(@PathVariable String name) {
        List<RestaurantModel> restaurants = restaurantService.listRestaurantByName(name);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{idRestaurant}")
    public ResponseEntity<RestaurantModel> getRestaurantById(@PathVariable String idRestaurant) {
        RestaurantModel restaurant = restaurantService.getRestaurantById(idRestaurant);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<RestaurantModel> getRestaurantByEmail(@PathVariable String email) {
        RestaurantModel restaurant = restaurantService.getRestaurantByEmail(email);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }
}
