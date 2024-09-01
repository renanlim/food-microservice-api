package com.cefet.ms_order.interfaces;

import com.cefet.ms_order.model.OrderItemModel;

import java.util.List;

public interface IOrderItemService {
    List<OrderItemModel> getItems(String idOrder);
    void addItem(String idOrder, String idItem, int quantity);
    void removeItem(String idOrder, String idItem);
    void changeItemQuantity(String idOrder, String idItem, int quantity);
    Double calculateTotal(String idOrder);
}
