package com.nhnacademy.edu.springframework.repository;

import java.util.Collection;

public interface WaterBillRepository {
    void load();

    Collection<WaterBill> findAll();
}
