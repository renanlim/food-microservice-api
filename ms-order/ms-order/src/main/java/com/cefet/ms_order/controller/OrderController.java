package com.cefet.ms_order.controller;

import com.cefet.ms_order.facade.OrderFacade;
import com.cefet.ms_order.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @PostMapping("/restaurante/{idRestaurant}/cliente/{idCustomer}")
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order, @PathVariable String idRestaurant, @PathVariable String idCustomer) {
        OrderModel createdOrder = orderFacade.createOrder(order, idRestaurant, idCustomer);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("idOrder") String idOrder) {
        Optional<OrderModel> order = orderFacade.getOrderById(idOrder);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> listOrders() {
        List<OrderModel> orders = orderFacade.listOrders();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{idOrder}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("idOrder") String idOrder) {
        orderFacade.deleteOrder(idOrder);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idOrder}")
    public ResponseEntity<OrderModel> updateOrder(@PathVariable String idOrder, @RequestBody OrderModel updatedOrder) {
        OrderModel order = orderFacade.updateOrder(idOrder, updatedOrder);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{idOrder}/cancelar")
    public ResponseEntity<String> cancelOrderByClient(@PathVariable String idOrder) {
        try {
            OrderModel canceledOrder = orderFacade.cancelOrder(idOrder, true);
            return ResponseEntity.ok("Pedido cancelado com sucesso: " + canceledOrder.getIdOrder());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{idOrder}/negar")
    public ResponseEntity<String> cancelOrderByRestaurant(@PathVariable String idOrder) {
        try {
            OrderModel deniedOrder = orderFacade.cancelOrder(idOrder, false);
            return ResponseEntity.ok("Pedido negado com sucesso: " + deniedOrder.getIdOrder());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
