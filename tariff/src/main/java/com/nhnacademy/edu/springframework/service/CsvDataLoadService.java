package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.repository.WaterBillRepository;
import org.springframework.stereotype.Service;

@Service
public class CsvDataLoadService implements DataLoadService {

    private final WaterBillRepository waterBillRepository;

    public CsvDataLoadService(WaterBillRepository waterBillRepository) {
        this.waterBillRepository = waterBillRepository;
    }

    @Override
    public void load() {
        waterBillRepository.load();
    }
}
