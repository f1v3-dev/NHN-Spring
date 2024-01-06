package com.nhnacademy.edu.springframework.report;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.util.Collection;

public interface ResultReport {

    void report(Collection<WaterBill> waterBills);
}
