package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.parser.DataParser;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultWaterBillRepository implements WaterBillRepository {

    private Collection<WaterBill> billList = new ArrayList<>();

    DataParser parser;

    public DefaultWaterBillRepository(@Qualifier("csvDataParser") DataParser parser) {
        this.parser = parser;
    }


    @Override
    public void load() {
        billList = parser.parse("Tariff_20220331.csv");
    }

    @Override
    public Collection<WaterBill> findAll() {
        return billList;
    }
}
