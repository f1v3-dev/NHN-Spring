package com.nhnacademy.edu.springframework.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class GradeQueryServiceTest {

    @InjectMocks
    DefaultGradeQueryService service;

    @Mock
    Scores scores;

    @Mock
    Students students;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("GradeQueryService - getScoreByStudentName test")
    void getScoreByStudentName() {

        when(students.findAll()).thenReturn(List.of(new Student(1, "A"), new Student(2, "A"), new Student(3, "B")));

        List<Score> findStudents = service.getScoreByStudentName("A");

        Mockito.verify(students, Mockito.times(1)).findAll();

        assertEquals(2, findStudents.size());
    }

    @Test
    @DisplayName("GradeQueryService - getScoreByStudentSeq test")
    void getScoreByStudentSeq() {

        when(scores.findAll()).thenReturn(List.of(new Score(1, 100), new Score(2, 80)));

        Score findScore = service.getScoreByStudentSeq(1);

        Mockito.verify(scores, Mockito.times(1)).findAll();

        assertEquals(100, findScore.getScore());
    }
}