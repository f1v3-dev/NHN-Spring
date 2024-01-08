package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute
    public Student getStudent(@PathVariable("studentId") Long id) {
        Student student = studentRepository.getStudent(id);

        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

        return student;
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@ModelAttribute Student student,
                              @RequestParam(name = "hideScore", required = false) String hide,
                              ModelMap modelMap) {

        if (Objects.isNull(hide)) {
            modelMap.put("student", student);
        } else if (hide.equals("yes")) {
            Student hideStudent = Student.constructHideScoreStudent(student.getName(), student.getEmail());
            modelMap.put("student", hideStudent);
        }

        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public ModelAndView studentModifyForm(@ModelAttribute Student student) {

        ModelAndView mav = new ModelAndView("studentModify");
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/{studentId}/modify")
    public String modifyUser(@ModelAttribute Student student,
                             @Valid @ModelAttribute StudentRequest studentRequest,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        student.setName(studentRequest.getName());
        student.setScore(studentRequest.getScore());
        student.setEmail(studentRequest.getEmail());
        student.setComment(studentRequest.getComment());

        studentRepository.modify(student);

        model.addAttribute("student", student);
        return "studentView";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(StudentNotFoundException ex, Model model) {

        model.addAttribute("exception", ex);
        return "error";
    }
}
