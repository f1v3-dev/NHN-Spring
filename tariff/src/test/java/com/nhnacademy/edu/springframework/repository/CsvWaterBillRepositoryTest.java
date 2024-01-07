package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.parser.DataParser;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CsvWaterBillRepositoryTest {

    @InjectMocks
    DefaultWaterBillRepository waterBillRepository;

    @Mock
    DataParser csvDataParser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }


}
