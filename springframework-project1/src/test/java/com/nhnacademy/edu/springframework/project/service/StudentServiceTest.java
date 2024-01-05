package com.nhnacademy.edu.springframework.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

    StudentService service;

    @BeforeEach
    public void init() {
        service = new DefaultStudentService();

        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
    }

    @AfterEach
    public void clear() {
        CsvScores.getInstance().findAll().clear();
        CsvStudents.getInstance().findAll().clear();
    }

    @Test
    @DisplayName("StudentService - getPassedStudent test")
    void getPassedStudents() {
        Collection<Student> passedStudents = service.getPassedStudents();
        assertEquals(2, passedStudents.size());
    }

    @Test
    @DisplayName("StudentService - getStudentsOrderByScore")
    void getStudentsOrderByScore() {
        Collection<Student> students = service.getStudentsOrderByScore();
        assertEquals(3, students.size());
    }

}