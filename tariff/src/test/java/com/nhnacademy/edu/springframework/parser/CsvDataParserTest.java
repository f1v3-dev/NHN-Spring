package com.nhnacademy.edu.springframework.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class CsvDataParserTest {

    CsvDataParser parser;

    @BeforeEach
    void setup() {
        parser = new CsvDataParser();
    }

    @Test
    @DisplayName("CsvDataParser - parse test")
    void parserTest() {
        List<WaterBill> parseList = parser.parse("Tariff_20220331.csv");
        assertEquals(20, parseList.size());

        WaterBill waterBill = parseList.get(0);
        assertAll(
                () -> assertEquals("동두천시", waterBill.getCity()),
                () -> assertEquals("가정용", waterBill.getSector()),
                () -> assertEquals(1, waterBill.getBeginSection()),
                () -> assertEquals(20, waterBill.getEndSection()),
                () -> assertEquals(690, waterBill.getUnitPrice())
        );
    }

    @Test
    @DisplayName("CsvDataParser - createWaterBill test")
    void createWaterBillTest() {
        String line = "350, 광주광역시 , 욕탕용 ,1,412,999999,2170,";

        WaterBill waterBill = ReflectionTestUtils.invokeMethod(parser, "createWaterBill", line);

        assertAll(
                () -> assertEquals("광주광역시", waterBill.getCity()),
                () -> assertEquals("욕탕용", waterBill.getSector()),
                () -> assertEquals(412, waterBill.getBeginSection()),
                () -> assertEquals(999999, waterBill.getEndSection()),
                () -> assertEquals(2170, waterBill.getUnitPrice()),
                () -> assertTrue(waterBill.isInRange(10000))
        );
    }

}