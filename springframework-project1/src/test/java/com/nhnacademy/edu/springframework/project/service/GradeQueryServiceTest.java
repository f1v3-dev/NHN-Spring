package com.nhnacademy.edu.springframework.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GradeQueryServiceTest {

    GradeQueryService service;

    @BeforeEach
    public void init() {
        service = new DefaultGradeQueryService();

        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
    }
    
    @AfterEach
    public void clear() {
        CsvScores.getInstance().findAll().clear();
        CsvStudents.getInstance().findAll().clear();
    }

    @Test
    @DisplayName("GradeQueryService - getScoreByStudentName test")
    void getScoreByStudentName() {

        List<Score> findStudentList = service.getScoreByStudentName("A");
        assertEquals(2, findStudentList.size());
    }

    @Test
    @DisplayName("GradeQueryService - getScoreByStudentSeq test")
    void getScoreByStudentSeq() {
        Score findScore = service.getScoreByStudentSeq(2);
        assertEquals(80, findScore.getScore());

        Score nullScore = service.getScoreByStudentSeq(4);
        assertNull(nullScore);
        assertThrows(NullPointerException.class, () -> nullScore.getScore());
    }

}