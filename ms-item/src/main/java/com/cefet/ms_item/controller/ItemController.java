package com.cefet.ms_item.controller;

import com.cefet.ms_item.model.ItemModel;
import com.cefet.ms_item.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemModel> createItem(@RequestBody ItemModel item) {
        ItemModel createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PostMapping("/restaurante/{idRestaurant}")
    public ResponseEntity<ItemModel> createItemByRestaurant(@RequestBody ItemModel item, @PathVariable String idRestaurant) {
        ItemModel createdItem = itemService.createItemByRestaurant(item, idRestaurant);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping("/restaurante/{idRestaurant}")
    public ResponseEntity<List<ItemModel>> getItemsByRestaurant(@PathVariable String idRestaurant) {
        List<ItemModel> items = itemService.getItemsByRestaurant(idRestaurant);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<ItemModel> getItemById(@PathVariable String idItem) {
        ItemModel item = itemService.getItemById(idItem);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idItem}")
    public ResponseEntity<ItemModel> updateItem(@PathVariable String idItem, @RequestBody ItemModel item) {
        ItemModel updatedItem = itemService.updateItem(idItem, item);
        if (updatedItem != null) {
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Void> deleteItemById(@PathVariable String idItem) {
        Boolean deleted = itemService.deleteItemById(idItem);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/restaurante/{idRestaurant}/item/{idItem}")
    public ResponseEntity<Void> deleteItemByRestaurant(@PathVariable String idRestaurant, @PathVariable String idItem) {
        Boolean deleted = itemService.deleteItemByRestaurant(idRestaurant, idItem);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemModel>> getAllItems() {
        List<ItemModel> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
