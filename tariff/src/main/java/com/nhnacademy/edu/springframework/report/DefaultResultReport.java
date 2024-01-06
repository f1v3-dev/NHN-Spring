package com.nhnacademy.edu.springframework.report;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.util.Collection;
import org.springframework.stereotype.Component;

@Component
public class DefaultResultReport implements ResultReport {

    @Override
    public void report(Collection<WaterBill> waterBills) {
        waterBills.forEach(System.out::println);
    }
}
