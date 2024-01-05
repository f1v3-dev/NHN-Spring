package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DefaultGradeQueryService implements GradeQueryService {


    private final Students students;
    private final Scores scores;

    public DefaultGradeQueryService(Students students, Scores scores) {
        this.students = students;
        this.scores = scores;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {

        return students.findAll()
                .stream()
                .filter(student -> student.getName().equals(name))
                .map(Student::getScore)
                .collect(Collectors.toList());
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {

        Optional<Score> optionalScore = scores.findAll()
                .stream()
                .filter(score -> seq == score.getStudentSeq())
                .findAny();

        return optionalScore.orElse(null);
    }
}
