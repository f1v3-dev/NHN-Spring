package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.repository.WaterBillRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultDataLoadService implements DataLoadService {

    private final WaterBillRepository waterBillRepository;

    public DefaultDataLoadService(WaterBillRepository waterBillRepository) {
        this.waterBillRepository = waterBillRepository;
    }

    @Override
    public void load() {
        waterBillRepository.load();
    }
}
