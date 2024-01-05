package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DataLoadServiceTest {

    @InjectMocks
    CsvDataLoadService service;

    @Mock
    Scores scores;

    @Mock
    Students students;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("DataLoadService - loadAndMerge test")
    void loadAndMerge() {
        service.loadAndMerge();

        Mockito.verify(scores, Mockito.times(1)).load();
        Mockito.verify(students, Mockito.times(1)).load();
        Mockito.verify(students, Mockito.times(1)).merge(scores.findAll());
    }


}