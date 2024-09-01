package com.cefet.ms_order.repositories;

import com.cefet.ms_order.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, String> {
}
