package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetail.Pk> {
}
