package com.cefet.ms_order.repositories;

import com.cefet.ms_order.model.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemModel, String> {
}
