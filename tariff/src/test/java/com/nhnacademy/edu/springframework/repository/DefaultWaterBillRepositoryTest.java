package com.nhnacademy.edu.springframework.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Matchers.anyString;

import com.nhnacademy.edu.springframework.parser.CsvDataParser;
import com.nhnacademy.edu.springframework.parser.DataParser;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DefaultWaterBillRepositoryTest {

    @InjectMocks
    DefaultWaterBillRepository waterBillRepository;

    @Mock
    DataParser parser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - load test")
    void loadTest() {
        waterBillRepository.load();

        Mockito.verify(parser, Mockito.times(1)).parse(anyString());


        // 여기 어떻게 해야되지
        DefaultWaterBillRepository test = new DefaultWaterBillRepository(new CsvDataParser());
        test.load();
        Collection<WaterBill> bills = test.findAll();
        assertEquals(20, bills.size());
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - findAll test")
    void findAllTest() {
        Collection<WaterBill> bills = waterBillRepository.findAll();
        assertInstanceOf(ArrayList.class, bills);
    }
}