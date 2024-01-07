package com.nhnacademy.edu.springframework.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class JsonDataParserTest {

    JsonDataParser jsonDataParser;

    @BeforeEach
    void setup() {
        jsonDataParser = new JsonDataParser();
    }


    @Test
    @DisplayName("JsonDataParser - parse test")
    void parserTest() {
        List<WaterBill> parseList = jsonDataParser.parse("Tariff_20220331.json");
        assertEquals(20, parseList.size());

        WaterBill waterBill = parseList.get(0);
        assertAll(
                () -> assertEquals(1, waterBill.getId()),
                () -> assertEquals("동두천시", waterBill.getCity()),
                () -> assertEquals("가정용", waterBill.getSector()),
                () -> assertEquals(1, waterBill.getStep()),
                () -> assertEquals(1, waterBill.getBeginSection()),
                () -> assertEquals(20, waterBill.getEndSection()),
                () -> assertEquals(690, waterBill.getUnitPrice()),
                () -> assertEquals(0, waterBill.getBasicPriceByStep())
        );
    }

    @Test
    @DisplayName("JsonDataParser - setDefaultValue test")
    void setDefaultValueTest() {
        WaterBill waterBill = new WaterBill();
        waterBill.setBasicPriceByStep(null);
        waterBill.setBillTotal(null);

        ReflectionTestUtils.invokeMethod(jsonDataParser, "setDefaultValue", List.of(waterBill));

        assertAll(
                () -> assertEquals(0, waterBill.getBasicPriceByStep()),
                () -> assertEquals(0, waterBill.getBillTotal())
        );
    }
}