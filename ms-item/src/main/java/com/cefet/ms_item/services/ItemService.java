package com.cefet.ms_item.services;

import com.cefet.ms_item.interfaces.RestaurantClient;
import com.cefet.ms_item.model.ItemModel;
import com.cefet.ms_item.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final RestaurantClient restaurantClient;

    public ItemModel getItemById(String idItem) {
        return itemRepository.findById(idItem).orElse(null);
    }

    public Boolean deleteItemById(String idItem) {
        if (itemRepository.existsById(idItem)) {
            itemRepository.deleteById(idItem);
            return true;
        }
        return false;
    }

    public ItemModel createItem(ItemModel item) {
        return itemRepository.save(item);
    }

    public ItemModel createItemByRestaurant(ItemModel item, String idRestaurant) {
        String restaurant = restaurantClient.getRestaurantById(idRestaurant);

        if (restaurant == null || restaurant.isEmpty()) {
            throw new RuntimeException("Restaurante não encontrado");
        }
        item.setIdRestaurant(idRestaurant);
        return itemRepository.save(item);
    }

    public Boolean deleteItemByRestaurant(String idRestaurant, String idItem) {
        ItemModel item = itemRepository.findById(idItem).orElse(null);
        if (item != null && item.getIdRestaurant().equals(idRestaurant)) {
            itemRepository.deleteById(idItem);
            return true;
        }
        return false;
    }

    public ItemModel updateItem(String idItem, ItemModel updatedItem) {
        return itemRepository.findById(idItem)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setDescription(updatedItem.getDescription());
                    item.setPrice(updatedItem.getPrice());
                    item.setIdRestaurant(updatedItem.getIdRestaurant());
                    return itemRepository.save(item);
                })
                .orElse(null);
    }

    public List<ItemModel> getAllItems() {
        return itemRepository.findAll();
    }

    public List<ItemModel> getItemsByRestaurant(String idRestaurant) {
        String restaurant = restaurantClient.getRestaurantById(idRestaurant);

        if (restaurant == null || restaurant.isEmpty()) {
            throw new RuntimeException("Restaurante não encontrado");
        }

        return itemRepository.findByIdRestaurant(idRestaurant);
    }
}
