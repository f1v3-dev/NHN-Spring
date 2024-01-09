package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class StudentRepositoryImpl implements StudentRepository {

    private final Map<Long, Student> students = new HashMap<>();


    @Override
    public boolean exists(long id) {
        return students.containsKey(id);
    }

    @Override
    public Student register(String name, String email, int score, String evaluation) {
        long id = students.keySet()
                .stream()
                .max(Comparator.comparing(Function.identity()))
                .map(l -> l + 1)
                .orElse(1L);

        Student student = Student.create(name, email, score, evaluation);
        student.setId(id);

        students.put(id, student);
        return student;
    }

    @Override
    public Student getStudent(long id) {
        return exists(id) ? students.get(id) : null;
    }

    @Override
    public void modify(Long studentId, StudentRequest studentRequest) {
        Student originStudent = getStudent(studentId);
        if (Objects.isNull(originStudent) || !exists(studentId)) {
            throw new StudentNotFoundException();
        }

        originStudent.setName(studentRequest.getName());
        originStudent.setEmail(studentRequest.getEmail());
        originStudent.setScore(studentRequest.getScore());
        originStudent.setComment(studentRequest.getComment());
    }
}
