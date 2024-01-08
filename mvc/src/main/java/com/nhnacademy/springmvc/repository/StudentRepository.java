package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Student;

public interface StudentRepository {

    boolean exists(long id);

    Student register(String name, String email, int score, String evaluation);

    Student getStudent(long id);

    void modify(Student student);

}
