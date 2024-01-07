package com.nhnacademy.edu.springframework.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;

import com.nhnacademy.edu.springframework.parser.CsvDataParser;
import com.nhnacademy.edu.springframework.parser.DataParser;
import com.nhnacademy.edu.springframework.parser.JsonDataParser;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class DefaultWaterBillRepositoryTest {

    DefaultWaterBillRepository waterBillRepository;

    DataParser csvDataParser;

    DataParser jsonDataParser;

    @BeforeEach
    void setup() {
        csvDataParser = new CsvDataParser();
        jsonDataParser = new JsonDataParser();
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - constructor test (csv)")
    void csvConstructorTest() {

        waterBillRepository = new DefaultWaterBillRepository(
                "*.csv", csvDataParser, jsonDataParser);

        Object findParser = ReflectionTestUtils.getField(waterBillRepository, "parser");

        assertInstanceOf(CsvDataParser.class, findParser);
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - constructor test (json)")
    void jsonConstructorTest() {

        waterBillRepository = new DefaultWaterBillRepository(
                "*.json", csvDataParser, jsonDataParser);

        Object findParser = ReflectionTestUtils.getField(waterBillRepository, "parser");

        assertInstanceOf(JsonDataParser.class, findParser);
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - constructor test (unsupported)")
    void invalidConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> new DefaultWaterBillRepository(
                "*.txt", csvDataParser, jsonDataParser), "Unsupported file type: *.txt");
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - load test (json)")
    void jsonLoadTest() {
        waterBillRepository = new DefaultWaterBillRepository(
                "Tariff_20220331.json", csvDataParser, jsonDataParser);

        Collection<WaterBill> beforeBills = waterBillRepository.findAll();
        assertEquals(0, beforeBills.size());

        waterBillRepository.load();

        Collection<WaterBill> afterBills = waterBillRepository.findAll();
        assertEquals(20, afterBills.size());
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - load test (csv)")
    void csvLoadTest() {
        waterBillRepository = new DefaultWaterBillRepository(
                "Tariff_20220331.csv", csvDataParser, jsonDataParser);

        Collection<WaterBill> beforeBills = waterBillRepository.findAll();
        assertEquals(0, beforeBills.size());

        waterBillRepository.load();

        Collection<WaterBill> afterBills = waterBillRepository.findAll();
        assertEquals(20, afterBills.size());
    }

    @Test
    @DisplayName("DefaultWaterBillRepository - findAll test")
    void findAllTest() {
        waterBillRepository = new DefaultWaterBillRepository(
                "*.csv", any(), any()
        );

        Collection<WaterBill> waterBills = waterBillRepository.findAll();
        assertInstanceOf(Collection.class, waterBills);
    }
}