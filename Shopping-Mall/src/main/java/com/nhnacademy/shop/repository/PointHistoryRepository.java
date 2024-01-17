package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.PointHistory;
import com.nhnacademy.shop.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Integer> {
    Optional<PointHistory> findByUser_UserIdAndDescription(String userId, String description);
}
