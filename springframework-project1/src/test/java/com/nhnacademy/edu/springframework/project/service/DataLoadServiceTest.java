package com.nhnacademy.edu.springframework.project.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataLoadServiceTest {

    DataLoadService service;

    @BeforeEach
    public void setup() {
        service = new CsvDataLoadService();
    }

    @AfterEach
    public void clear() {
        CsvScores.getInstance().findAll().clear();
        CsvStudents.getInstance().findAll().clear();
    }

    @Test
    @DisplayName("DataLoadService - loadAndMerge test")
    void loadAndMerge() {
        Scores scores = CsvScores.getInstance();
        Students students = CsvStudents.getInstance();
        assertAll(() -> assertEquals(0, scores.findAll().size()), () -> assertEquals(0, students.findAll().size()));

        service.loadAndMerge();
        assertAll(() -> assertEquals(3, scores.findAll().size()), () -> assertEquals(4, students.findAll().size()));
    }


}