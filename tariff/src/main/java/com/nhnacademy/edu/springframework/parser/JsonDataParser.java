package com.nhnacademy.edu.springframework.parser;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JsonDataParser implements DataParser {

    @Override
    public List<WaterBill> parse(String filePath) {

        return null;
    }
}
