package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.repository.WaterBillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CsvDataLoadServiceTest {

    @InjectMocks
    CsvDataLoadService dataLoadService;

    @Mock
    WaterBillRepository waterBillRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("CsvDataLoadService - load test")
    void loadTest() {
        dataLoadService.load();
        Mockito.verify(waterBillRepository, Mockito.times(1)).load();
    }
}