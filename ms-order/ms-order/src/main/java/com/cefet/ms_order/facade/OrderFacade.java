package com.cefet.ms_order.facade;

import com.cefet.ms_order.model.OrderModel;
import com.cefet.ms_order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderFacade {

    private final OrderService orderService;

    @Autowired
    public OrderFacade(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderModel createOrder(OrderModel order, String idRestaurant, String idCustomer) {
        return orderService.createOrder(order, idRestaurant, idCustomer);
    }

    public OrderModel updateOrder(String idOrder, OrderModel updatedOrder) {
        return orderService.updateOrder(idOrder, updatedOrder);
    }

    public Optional<OrderModel> getOrderById(String idOrder) {
        return orderService.getOrderById(idOrder);
    }

    public List<OrderModel> listOrders() {
        return orderService.listOrders();
    }

    public void deleteOrder(String idOrder) {
        orderService.deleteOrder(idOrder);
    }

    public OrderModel cancelOrder(String idOrder, boolean isClient) {
        return orderService.cancelOrder(idOrder, isClient);
    }
}
