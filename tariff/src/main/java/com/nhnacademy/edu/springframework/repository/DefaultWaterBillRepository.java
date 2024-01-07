package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.parser.DataParser;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultWaterBillRepository implements WaterBillRepository {

    private final DataParser parser;
    private final String filePath;
    private Collection<WaterBill> billList = new ArrayList<>();


    public DefaultWaterBillRepository(@Value("${file.path}") String filePath,
                                      @Qualifier("csvDataParser") DataParser csvDataParser,
                                      @Qualifier("jsonDataParser") DataParser jsonDataParser) {

        this.filePath = filePath;

        if (filePath.contains(".csv")) {
            this.parser = csvDataParser;
        } else if (filePath.contains(".json")) {
            this.parser = jsonDataParser;
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + filePath);
        }
    }


    @Override
    public void load() {
        billList = parser.parse(filePath);
    }

    @Override
    public Collection<WaterBill> findAll() {
        return billList;
    }
}
