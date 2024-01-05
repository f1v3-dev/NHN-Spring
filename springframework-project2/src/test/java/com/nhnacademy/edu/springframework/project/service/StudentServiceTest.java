package com.nhnacademy.edu.springframework.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class StudentServiceTest {

    @InjectMocks
    DefaultStudentService service;

    @Mock
    Students studentRepository;


    List<Student> studentList;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Student firstStudent = new Student(1, "A");
        firstStudent.setScore(new Score(1, 80));

        Student secondStudent = new Student(2, "B");
        secondStudent.setScore(new Score(2, 50));

        Student thirdStudent = new Student(3, "A");
        thirdStudent.setScore(new Score(3, 90));

        studentList = List.of(firstStudent, secondStudent, thirdStudent);
    }


    @Test
    @DisplayName("StudentService - getPassedStudent test")
    void getPassedStudents() {

        when(studentRepository.findAll())
                .thenReturn(studentList);

        Collection<Student> passedStudents = service.getPassedStudents();

        assertEquals(2, passedStudents.size());
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("StudentService - getStudentsOrderByScore")
    void getStudentsOrderByScore() {

        when(studentRepository.findAll())
                .thenReturn(studentList);

        Collection<Student> students = service.getStudentsOrderByScore();

        assertEquals(3, students.size());
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }

}