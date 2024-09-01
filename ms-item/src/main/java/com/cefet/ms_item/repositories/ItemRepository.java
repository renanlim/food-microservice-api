package com.cefet.ms_item.repositories;

import com.cefet.ms_item.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemModel, String> {
    List<ItemModel> findByIdRestaurant(String idRestaurant);
}
