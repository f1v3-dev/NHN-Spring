package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.util.Collection;

public interface WaterBillService {

    void calculateTariff(int usage);
}
