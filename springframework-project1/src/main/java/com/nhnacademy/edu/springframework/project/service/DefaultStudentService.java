package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    @Override
    public Collection<Student> getPassedStudents() {
        Students studentRepository = CsvStudents.getInstance();
        // TODO 1 : pass한 학생만 반환하도록 수정하세요.

        return studentRepository.findAll()
                .stream()
                .filter(student -> Objects.nonNull(student.getScore()) && !(student.getScore().isFail()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Students studentRepository = CsvStudents.getInstance();
        // TODO 4 : 성적 순으로 학생 정보(Student)를 반환합니다.

        return studentRepository.findAll()
                .stream()
                .filter(student -> Objects.nonNull(student.getScore()))
                .sorted(Comparator.comparing(student -> student.getScore().getScore()))
                .collect(Collectors.toList());
    }

}
