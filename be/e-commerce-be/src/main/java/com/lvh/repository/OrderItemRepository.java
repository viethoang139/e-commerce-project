package com.lvh.repository;

import com.lvh.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Set<OrderItem> findByOrderId(Long orderId);
}
