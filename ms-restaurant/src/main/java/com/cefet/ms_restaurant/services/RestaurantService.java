package com.cefet.ms_restaurant.services;

import com.cefet.ms_restaurant.model.RestaurantModel;
import com.cefet.ms_restaurant.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantModel registerRestaurant(RestaurantModel restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public RestaurantModel updateRestaurant(String idRestaurant, RestaurantModel restaurantUpdated, boolean isPartial) {
        Optional<RestaurantModel> optionalRestaurant = restaurantRepository.findById(idRestaurant);
        if (optionalRestaurant.isPresent()) {
            RestaurantModel restaurant = optionalRestaurant.get();

            if (isPartial) {
                if (restaurantUpdated.getName() != null) {
                    restaurant.setName(restaurantUpdated.getName());
                }
                if (restaurantUpdated.getAddress() != null) {
                    restaurant.setAddress(restaurantUpdated.getAddress());
                }
                if (restaurantUpdated.getCellphone() != null) {
                    restaurant.setCellphone(restaurantUpdated.getCellphone());
                }
                if (restaurantUpdated.getOpeningHours() != null) {
                    restaurant.setOpeningHours(restaurantUpdated.getOpeningHours());
                }
                if (restaurantUpdated.getEmail() != null) {
                    restaurant.setEmail(restaurantUpdated.getEmail());
                }
                if (restaurantUpdated.getPassword() != null) {
                    restaurant.setPassword(restaurantUpdated.getPassword());
                }
            } else {
                restaurant.setName(restaurantUpdated.getName());
                restaurant.setAddress(restaurantUpdated.getAddress());
                restaurant.setCellphone(restaurantUpdated.getCellphone());
                restaurant.setOpeningHours(restaurantUpdated.getOpeningHours());
                restaurant.setEmail(restaurantUpdated.getEmail());
                restaurant.setPassword(restaurantUpdated.getPassword());
            }

            return restaurantRepository.save(restaurant);
        }
        return null;
    }

    public void deleteRestaurant(String idRestaurant) {
        restaurantRepository.deleteById(idRestaurant);
    }

    public List<RestaurantModel> listRestaurant() {
        return restaurantRepository.findAll();
    }

    public List<RestaurantModel> listRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public RestaurantModel getRestaurantById(String idRestaurant) {
        return restaurantRepository.findById(idRestaurant).orElse(null);
    }

    public RestaurantModel getRestaurantByEmail(String email) {
        return restaurantRepository.findByEmail(email).orElse(null);
    }
}

