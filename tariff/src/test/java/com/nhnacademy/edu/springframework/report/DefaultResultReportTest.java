package com.nhnacademy.edu.springframework.report;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class DefaultResultReportTest {

    DefaultResultReport resultReport;

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

        ReflectionTestUtils.invokeMethod(resultReport, "report", waterBills);
    }

}