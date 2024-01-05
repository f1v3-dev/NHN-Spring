package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

    private final Students studentRepository;

    public DefaultStudentService(Students studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Collection<Student> getPassedStudents() {

        return studentRepository.findAll()
                .stream()
                .filter(student -> Objects.nonNull(student.getScore()) && !(student.getScore().isFail()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {

        return studentRepository.findAll()
                .stream()
                .filter(student -> Objects.nonNull(student.getScore()))
                .sorted(Comparator.comparing(student -> student.getScore().getScore()))
                .collect(Collectors.toList());
    }

}
