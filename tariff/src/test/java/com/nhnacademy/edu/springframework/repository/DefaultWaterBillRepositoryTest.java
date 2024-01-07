package com.nhnacademy.edu.springframework.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.springframework.file.FilePath;
import com.nhnacademy.edu.springframework.parser.CsvDataParser;
import com.nhnacademy.edu.springframework.parser.DataParser;
import com.nhnacademy.edu.springframework.parser.JsonDataParser;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultWaterBillRepositoryTest {

    DefaultWaterBillRepository waterBillRepository;

    DataParser csvDataParser;

    DataParser jsonDataParser;

    FilePath filePath;

    @BeforeEach
    void setup() {
        csvDataParser = mock(CsvDataParser.class);
        jsonDataParser = mock(JsonDataParser.class);
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - constructor test (csv)")
    void csvConstructorTest() {

        filePath = new FilePath("Tariff_20220331.csv");
        waterBillRepository = new DefaultWaterBillRepository(filePath, csvDataParser, jsonDataParser);

        assertEquals(filePath, waterBillRepository.getFilePath());
        assertInstanceOf(CsvDataParser.class, waterBillRepository.getParser());
        assertFalse(waterBillRepository.getParser() instanceof JsonDataParser);

    }

    @Test
    @DisplayName("DefaultWaterBillRepository - constructor test (json)")
    void jsonConstructorTest() {

        filePath = new FilePath("Tariff_20220331.json");
        waterBillRepository = new DefaultWaterBillRepository(filePath, csvDataParser, jsonDataParser);

        assertEquals(filePath, waterBillRepository.getFilePath());
        assertInstanceOf(JsonDataParser.class, waterBillRepository.getParser());
        assertFalse(waterBillRepository.getParser() instanceof CsvDataParser);
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - constructor test (unsupported)")
    void invalidConstructorTest() {

        filePath = new FilePath("*.txt");

        assertThrows(IllegalArgumentException.class,
                () -> new DefaultWaterBillRepository(filePath, csvDataParser, jsonDataParser),
                "Unsupported file type: *.txt");
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - load test (json)")
    void jsonLoadTest() {

        filePath = new FilePath("Tariff_20220331.json");
        waterBillRepository = new DefaultWaterBillRepository(filePath, csvDataParser, jsonDataParser);

        waterBillRepository.load();

        verify(jsonDataParser, times(1)).parse(any());
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - load test (csv)")
    void csvLoadTest() {

        filePath = new FilePath("Tariff_20220331.csv");
        waterBillRepository = new DefaultWaterBillRepository(filePath, csvDataParser, jsonDataParser);


        waterBillRepository.load();

        verify(csvDataParser, times(1)).parse(any());
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - findAll test")
    void findAllTest() {

        filePath = new FilePath("Tariff_20220331.csv");

        waterBillRepository = new DefaultWaterBillRepository(filePath, csvDataParser, jsonDataParser);

        Collection<WaterBill> waterBills = waterBillRepository.findAll();
        assertInstanceOf(Collection.class, waterBills);
    }
}