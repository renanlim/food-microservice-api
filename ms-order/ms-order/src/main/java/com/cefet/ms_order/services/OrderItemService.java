package com.cefet.ms_order.services;

import com.cefet.ms_order.dto.ItemDTO;
import com.cefet.ms_order.interfaces.IOrderItemService;
import com.cefet.ms_order.interfaces.ItemClient;
import com.cefet.ms_order.model.OrderItemModel;
import com.cefet.ms_order.model.OrderModel;
import com.cefet.ms_order.repositories.OrderItemRepository;
import com.cefet.ms_order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService implements IOrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemClient itemClient;

    @Autowired
    public OrderItemService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ItemClient itemClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemClient = itemClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemModel> getItems(String idOrder) {
        Optional<OrderModel> order = orderRepository.findById(idOrder);
        return order.map(OrderModel::getItems).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    @Transactional
    public void addItem(String idOrder, String idItem, int quantity) {
        Optional<OrderModel> orderOptional = orderRepository.findById(idOrder);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            ItemDTO itemDTO = itemClient.getItemById(idItem);
            if (itemDTO != null) {
                OrderItemModel orderItem = new OrderItemModel();
                orderItem.setIdItem(itemDTO.getIdItem());
                orderItem.setName(itemDTO.getName());
                orderItem.setPrice(itemDTO.getPrice());
                orderItem.setDescription(itemDTO.getDescription());
                orderItem.setQuantity(quantity);
                orderItem.setOrder(order);
                order.getItems().add(orderItem);
                orderRepository.save(order);
            } else {
                throw new RuntimeException("Item not found");
            }
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    @Override
    @Transactional
    public void removeItem(String idOrder, String idOrderItem) {
        Optional<OrderModel> orderOptional = orderRepository.findById(idOrder);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            boolean removed = order.getItems().removeIf(item -> item.getIdOrderItem().equals(idOrderItem));
            if (removed) {
                orderItemRepository.deleteById(idOrderItem);
                orderRepository.save(order);
            } else {
                throw new RuntimeException("Order item not found");
            }
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    @Override
    @Transactional
    public void changeItemQuantity(String idOrder, String idItem, int quantity) {
        Optional<OrderModel> orderOptional = orderRepository.findById(idOrder);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            for (OrderItemModel item : order.getItems()) {
                if (item.getIdItem().equals(idItem)) {
                    item.setQuantity(quantity);
                    orderRepository.save(order);
                    return;
                }
            }
            throw new RuntimeException("Item not found in order");
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Double calculateTotal(String idOrder) {
        Optional<OrderModel> orderOptional = orderRepository.findById(idOrder);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            return order.getItems().stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum();
        } else {
            throw new RuntimeException("Order not found");
        }
    }
}
