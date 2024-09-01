package com.cefet.ms_restaurant.repositories;

import com.cefet.ms_restaurant.model.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, String> {
    List<RestaurantModel> findByName(String name);
    Optional<RestaurantModel> findByEmail(String email);
}
