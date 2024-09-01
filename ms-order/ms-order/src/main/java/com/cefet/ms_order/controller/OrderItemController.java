package com.cefet.ms_order.controller;

import com.cefet.ms_order.model.OrderItemModel;
import com.cefet.ms_order.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/{idOrder}/item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemModel>> getItems(@PathVariable String idOrder) {
        List<OrderItemModel> items = orderItemService.getItems(idOrder);
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Void> addItem(@PathVariable String idOrder, @RequestParam String idItem, @RequestParam int quantity) {
        orderItemService.addItem(idOrder, idItem, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeItem(@PathVariable String idOrder, @RequestParam String idOrderItem) {
        orderItemService.removeItem(idOrder, idOrderItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> changeItemQuantity(@PathVariable String idOrder, @RequestParam String idItem, @RequestParam int quantity) {
        orderItemService.changeItemQuantity(idOrder, idItem, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Double> calculateTotal(@PathVariable String idOrder) {
        Double total = orderItemService.calculateTotal(idOrder);
        return ResponseEntity.ok(total);
    }
}
