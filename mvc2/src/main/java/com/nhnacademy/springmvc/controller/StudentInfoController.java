package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentInfoController {

    private final StudentRepository studentRepository;

    public StudentInfoController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * 학생 정보 등록: POST /students
     * 학생 정보 조회: GET /students/{studentId}
     * 학생 정보 수정: PUT /students/{studentId}
     */

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> viewStudent(@PathVariable("studentId") Long id) {
        Student student = studentRepository.getStudent(id);

        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

        return ResponseEntity.ok().body(student);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerStudent(@RequestBody StudentRegisterRequest student) {

        studentRepository.register(student.getName(),
                student.getEmail(),
                student.getScore(),
                student.getComment());
    }


    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyStudent(@PathVariable("studentId") Long id,
                              @Valid @RequestBody StudentRegisterRequest student,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        if (!studentRepository.exists(id)) {
            throw new StudentNotFoundException();
        }

        studentRepository.modify(id, student);
    }

}
