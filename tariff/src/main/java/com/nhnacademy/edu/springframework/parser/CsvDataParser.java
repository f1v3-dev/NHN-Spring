package com.nhnacademy.edu.springframework.parser;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvDataParser implements DataParser {


    @Override
    public List<WaterBill> parse(String filePath) {

        List<WaterBill> billList = new ArrayList<>();

        ClassLoader loader = getClass().getClassLoader();

        try (FileInputStream inputStream = new FileInputStream(loader.getResource(filePath).getFile());
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            // 첫 번째 라인 : Column Name - skip
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                WaterBill tariff = createWaterBill(line);
                billList.add(tariff);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return billList;
    }


    private static WaterBill createWaterBill(String line) {

        // 단계별 기본요금이 입력되어있지 않은 경우 0을 더해줌
        if (line.endsWith(",")) {
            line += "0";
        }

        String[] split = line.split(",");

        WaterBill waterBill = new WaterBill(
                Integer.parseInt(split[0]),
                split[1].trim(),
                split[2].trim(),
                Integer.parseInt(split[3]),
                Integer.parseInt(split[4]),
                Integer.parseInt(split[5]),
                Integer.parseInt(split[6]),
                Integer.parseInt(split[7])
        );

        if (waterBill.getBillTotal() == null) {
            waterBill.setBillTotal(0);
        }

        return waterBill;
    }
}
