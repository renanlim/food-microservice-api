package com.cefet.ms_restaurant.services;

import com.cefet.ms_restaurant.model.RestaurantModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacadeRestaurantService {

    private final RestaurantService restaurantService;

    @Autowired
    public FacadeRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public Optional<RestaurantModel> getRestaurantById(String id) {
        return Optional.ofNullable(restaurantService.getRestaurantById(id));
    }

    public List<RestaurantModel> getRestaurantsByName(String name) {
        return restaurantService.listRestaurantByName(name);
    }

    public RestaurantModel createRestaurant(RestaurantModel restaurantModel) {
        return restaurantService.registerRestaurant(restaurantModel);
    }

    public RestaurantModel updateRestaurant(String id, RestaurantModel restaurantModel, boolean isPartial) {
        return restaurantService.updateRestaurant(id, restaurantModel, isPartial);
    }

    public boolean deleteRestaurantById(String id) {
        try {
            restaurantService.deleteRestaurant(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<RestaurantModel> listAllRestaurants() {
        return restaurantService.listRestaurant();
    }
}
