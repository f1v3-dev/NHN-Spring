package com.nhnacademy.edu.springframework.project.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    public void init() {
        scores = CsvScores.getInstance();
    }

    @AfterEach
    public void clear() {
        scores.findAll().clear();
    }

    @Test
    @DisplayName("Scores - load test")
    void load() {
        assertEquals(0, scores.findAll().size());
        scores.load();
        assertEquals(3, scores.findAll().size());
    }


    @Test
    @DisplayName("Scores - findAll test")
    void findAll() {
        List<Score> scoreList = scores.findAll();

        assertAll(
                () -> assertInstanceOf(List.class, scoreList),
                () -> assertEquals(0, scoreList.size())
        );

        Score score = new Score(1, 90);
        scoreList.add(score);

        assertEquals(1, scores.findAll().size());
    }


}