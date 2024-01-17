package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Long countByCartId(int cartId);

    Long countByUser_id(String userId);

    List<Cart> findByUser_UserId(String userId);
}
