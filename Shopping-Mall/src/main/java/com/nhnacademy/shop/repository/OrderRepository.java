package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser_UserId(String userId);

    Long countByOrderId(int orderId);

    Long countByUser_UserId(String userId);
}
