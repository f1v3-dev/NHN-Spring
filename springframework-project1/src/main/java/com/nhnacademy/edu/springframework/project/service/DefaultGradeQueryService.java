package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {

    @Override
    public List<Score> getScoreByStudentName(String name) {
        // TODO 5: 학생 이름으로 점수를 반환합니다. 동명이인 고려합니다.

        Students students = CsvStudents.getInstance();

        return students.findAll()
                        .stream()
                        .filter(student -> student.getName().equals(name))
                        .map(Student::getScore)
                        .collect(Collectors.toList());
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        // TODO 6 : 학번으로 점수를 반환합니다. seq 인자가 학번입니다.

        Scores scores = CsvScores.getInstance();
        Optional<Score> optionalScore = scores.findAll()
                .stream()
                .filter(score -> seq == score.getStudentSeq())
                .findAny();

        return optionalScore.orElse(null);
    }
}
