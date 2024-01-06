package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.report.ResultReport;
import com.nhnacademy.edu.springframework.repository.WaterBill;
import com.nhnacademy.edu.springframework.repository.WaterBillRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DefaultWaterBillService implements WaterBillService {

    private final WaterBillRepository waterBillRepository;

    private final ResultReport resultReport;

    public DefaultWaterBillService(WaterBillRepository waterBillRepository, ResultReport resultReport) {
        this.waterBillRepository = waterBillRepository;
        this.resultReport = resultReport;
    }

    @Override
    public void calculateTariff(int usage) {

        List<WaterBill> findBills = waterBillRepository.findAll()
                .stream()
                .filter(waterBill -> waterBill.isInRange(usage))
                .sorted(Comparator.comparing(WaterBill::getUnitPrice))
                .limit(5)
                .collect(Collectors.toList());

        findBills.forEach(findBill -> findBill.setBillTotal(findBill.getUnitPrice() * usage));

        resultReport.report(findBills);
    }

}
