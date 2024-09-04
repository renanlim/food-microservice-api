package com.cefet.ms_order.services;

import com.cefet.ms_order.dto.ItemDTO;
import com.cefet.ms_order.interfaces.ItemClient;
import com.cefet.ms_order.model.OrderItemModel;
import com.cefet.ms_order.model.OrderModel;
import com.cefet.ms_order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemClient itemClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemClient itemClient) {
        this.orderRepository = orderRepository;
        this.itemClient = itemClient;
    }

    @Transactional
    public OrderModel createOrder(OrderModel order, String idRestaurant, String idCustomer) {
        List<OrderItemModel> updatedItems = new ArrayList<>();
        for (OrderItemModel item : order.getItems()) {
            ItemDTO itemDTO = itemClient.getItemById(item.getIdItem());
            if (itemDTO != null) {
                OrderItemModel orderItem = new OrderItemModel();
                orderItem.setIdItem(itemDTO.getIdItem());
                orderItem.setName(itemDTO.getName());
                orderItem.setPrice(itemDTO.getPrice());
                orderItem.setDescription(itemDTO.getDescription());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setOrder(order);
                updatedItems.add(orderItem);
            } else {
                throw new RuntimeException("Item com id " + item.getIdItem() + " não encontrado.");
            }
        }

        order.setItems(updatedItems);
        order.setIdRestaurant(idRestaurant);
        order.setIdCustomer(idCustomer);

        return orderRepository.save(order);
    }

    @Transactional
    public OrderModel updateOrder(String idOrder, OrderModel updatedOrder) {
        OrderModel existingOrder = orderRepository.findById(idOrder)
                .orElseThrow(() -> new RuntimeException("Pedido com id " + idOrder + " não encontrado."));
        if (updatedOrder.getIdRestaurant() != null) {
            existingOrder.setIdRestaurant(updatedOrder.getIdRestaurant());
        }
        if (updatedOrder.getIdCustomer() != null) {
            existingOrder.setIdCustomer(updatedOrder.getIdCustomer());
        }
        if (updatedOrder.getStatus() != null) {
            existingOrder.setStatus(updatedOrder.getStatus());
        }
        if (updatedOrder.getPayment() != null) {
            existingOrder.setPayment(updatedOrder.getPayment());
        }
        if (updatedOrder.getObservation() != null) {
            existingOrder.setObservation(updatedOrder.getObservation());
        }

        List<OrderItemModel> updatedItems = new ArrayList<>();
        for (OrderItemModel updatedItem : updatedOrder.getItems()) {
            ItemDTO itemDTO = itemClient.getItemById(updatedItem.getIdItem());
            if (itemDTO != null) {
                OrderItemModel orderItem = new OrderItemModel();
                orderItem.setIdItem(itemDTO.getIdItem());
                orderItem.setName(itemDTO.getName());
                orderItem.setPrice(itemDTO.getPrice());
                orderItem.setDescription(itemDTO.getDescription());
                orderItem.setQuantity(updatedItem.getQuantity());
                orderItem.setOrder(existingOrder);
                updatedItems.add(orderItem);
            } else {
                throw new RuntimeException("Item com id " + updatedItem.getIdItem() + " não encontrado.");
            }
        }

        existingOrder.setItems(updatedItems);

        return orderRepository.save(existingOrder);
    }


    @Transactional(readOnly = true)
    public Optional<OrderModel> getOrderById(String idOrder) {
        return orderRepository.findById(idOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderModel> listOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteOrder(String idOrder) {
        if (orderRepository.existsById(idOrder)) {
            orderRepository.deleteById(idOrder);
        } else {
            throw new RuntimeException("Pedido com id " + idOrder + " não encontrado.");
        }
    }

    @Transactional
    public OrderModel cancelOrder(String idOrder, boolean isClient) {
        OrderModel order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new RuntimeException("Pedido com id " + idOrder + " não encontrado."));

        if ("AGUARDANDO CONFIRMAÇÃO".equals(order.getStatus())) {
            if (isClient) {
                order.setStatus("CANCELADO");
            } else {
                order.setStatus("NEGADO");
            }
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Não é possível cancelar o pedido no status atual: " + order.getStatus());
        }
    }
}
