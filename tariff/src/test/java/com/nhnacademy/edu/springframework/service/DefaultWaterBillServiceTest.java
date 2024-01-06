package com.nhnacademy.edu.springframework.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.report.ResultReport;
import com.nhnacademy.edu.springframework.repository.WaterBill;
import com.nhnacademy.edu.springframework.repository.WaterBillRepository;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DefaultWaterBillServiceTest {

    @InjectMocks
    DefaultWaterBillService waterBillService;

    @Mock
    WaterBillRepository waterBillRepository;

    @Mock
    ResultReport resultReport;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("DefaultWaterBillService - calculateTariff test")
    void calculateTariffTest() {

        int testUsage = 30;
        waterBillService.calculateTariff(testUsage);
        verify(waterBillRepository, times(1)).findAll();
        verify(resultReport, times(1)).report(any());
    }

}