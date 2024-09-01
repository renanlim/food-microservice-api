package com.cefet.ms_order.controller;

import com.cefet.ms_order.model.OrderModel;
import com.cefet.ms_order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/restaurante/{idRestaurant}/cliente/{idCustomer}")
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order, @PathVariable String idRestaurant, @PathVariable String idCustomer) {
        OrderModel createdOrder = orderService.createOrder(order, idRestaurant, idCustomer);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("idOrder") String idOrder) {
        Optional<OrderModel> order = orderService.getOrderById(idOrder);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> listOrders() {
        List<OrderModel> orders = orderService.listOrders();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{idOrder}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("idOrder") String idOrder) {
        orderService.deleteOrder(idOrder);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idOrder}")
    public ResponseEntity<OrderModel> updateOrder(@PathVariable String idOrder, @RequestBody OrderModel updatedOrder) {
        OrderModel order = orderService.updateOrder(idOrder, updatedOrder);
        return ResponseEntity.ok(order);
    }
}
