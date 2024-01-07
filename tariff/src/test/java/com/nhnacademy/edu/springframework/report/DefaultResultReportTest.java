package com.nhnacademy.edu.springframework.report;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultResultReportTest {

    DefaultResultReport resultReport;

    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        resultReport = new DefaultResultReport();
    }

    @Test
    @DisplayName("DefaultResultReport - report test")
    void reportTest() {

        List<WaterBill> waterBills =
                List.of(new WaterBill(1, "광주광역시", "욕탕용", 1, 1, 412, 2170, 0),
                        new WaterBill(2, "화순군", "상업용", 5, 5, 50, 440, 0));

        System.setOut(new PrintStream(outputStreamCaptor));

        resultReport.report(waterBills);

        String expectedOutput = "WaterBill{city='광주광역시', sector='욕탕용', unitPrice=2170, billTotal=null}\n" +
                "WaterBill{city='화순군', sector='상업용', unitPrice=440, billTotal=null}";

        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

}