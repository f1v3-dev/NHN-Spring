package com.nhnacademy.edu.springframework.project.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentsTest {

    Students students;

    @BeforeEach
    public void setup() {
        students = CsvStudents.getInstance();
    }

    @AfterEach
    public void clear() {
        CsvScores.getInstance().findAll().clear();
        CsvStudents.getInstance().findAll().clear();
    }

    @Test
    @DisplayName("Students - load test")
    void load() {
        assertEquals(0, students.findAll().size());

        students.load();
        assertEquals(4, students.findAll().size());
    }

    @Test
    @DisplayName("Students - findAll test")
    void findAll() {

        Collection<Student> studentCollection = students.findAll();
        assertAll(
                () -> assertInstanceOf(Collection.class, studentCollection),
                () -> assertEquals(0, studentCollection.size())
        );

        Student student = new Student(20184493, "정승조");
        studentCollection.add(student);

        assertEquals(1, studentCollection.size());
    }

    @Test
    @DisplayName("Students - merge test")
    void merge() {

        CsvScores.getInstance().load();
        students.load();

        students.merge(CsvScores.getInstance().findAll());

        Collection<Student> studentCollection = students.findAll();
        assertEquals(4, studentCollection.size());
    }


}