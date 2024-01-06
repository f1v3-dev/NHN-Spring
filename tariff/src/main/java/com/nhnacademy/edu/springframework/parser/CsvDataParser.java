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

    private final List<WaterBill> billList = new ArrayList<>();

    @Override
    public List<WaterBill> parse(String filePath) {

        ClassLoader loader = getClass().getClassLoader();

        try (FileInputStream file = new FileInputStream(loader.getResource(filePath).getFile());
             BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))) {

            // 첫 번째 라인 : Column Name - skip
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                WaterBill tariff = getWaterBill(line);
                billList.add(tariff);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

        return billList;
    }


    private static WaterBill getWaterBill(String line) {
        String[] split = line.split(",");

        String city = split[1].trim();
        String sector = split[2].trim();
        int beginSection = Integer.parseInt(split[4]);
        int endSection = Integer.parseInt(split[5]);
        int unitCost = Integer.parseInt(split[6]);

        return new WaterBill(city, sector, beginSection, endSection, unitCost);
    }
}
