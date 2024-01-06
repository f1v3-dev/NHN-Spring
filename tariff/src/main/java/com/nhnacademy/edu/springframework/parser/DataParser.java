package com.nhnacademy.edu.springframework.parser;

import com.nhnacademy.edu.springframework.repository.WaterBill;
import java.util.List;

public interface DataParser {

    List<WaterBill> parse(String filePath);
}
