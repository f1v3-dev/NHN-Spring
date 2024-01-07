package com.nhnacademy.edu.springframework.parser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JsonDataParser implements DataParser {


    @Override
    public List<WaterBill> parse(String filePath) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            ClassLoader loader = getClass().getClassLoader();
            String realPath = loader.getResource(filePath).getFile();

            File file = new File(realPath);
            List<WaterBill> waterBills = mapper.readValue(file, new TypeReference<>() {});

            setDefaultValue(waterBills);

            return waterBills;
            } catch (JsonParseException e) {
            throw new RuntimeException("Json Parse Exception!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setDefaultValue(List<WaterBill> waterBills) {
        waterBills.forEach(waterBill -> {
            if (waterBill.getBasicPriceByStep() == null) {
                waterBill.setBasicPriceByStep(0);
            }

            if (waterBill.getBillTotal() == null) {
                waterBill.setBillTotal(0);
            }
        });
    }
}
