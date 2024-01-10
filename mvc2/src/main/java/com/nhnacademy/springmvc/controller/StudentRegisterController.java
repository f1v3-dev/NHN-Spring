package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String studentRegisterForm() {

        return "studentRegister";
    }

    @PostMapping
    public String registerStudent(@Valid @ModelAttribute StudentRegisterRequest student,
                                  BindingResult bindingResult,
                                  Model model) {

        log.info("student={}", student);


        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Student registeredStudent =
                studentRepository.register(student.getName(),
                                            student.getEmail(),
                                            student.getScore(),
                                            student.getComment());

        model.addAttribute("student", registeredStudent);

        return "redirect:/student/" + registeredStudent.getId();
    }


}
