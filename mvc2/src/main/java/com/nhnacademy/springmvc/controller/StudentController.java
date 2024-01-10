package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentId") Long id) {
        Student student = studentRepository.getStudent(id);

        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

        return student;
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@ModelAttribute("student") Student student,
                              ModelMap modelMap) {
        modelMap.put("student", student);
        return "/studentView";
    }

    @GetMapping(value = "/{studentId}", params = "hideScore=yes")
    public String hideScoreViewStudent(@ModelAttribute("student") Student student,
                                       ModelMap modelMap) {

        modelMap.put("student", student.hideScoreStudent());

        return "/studentView";
    }

    @GetMapping("/{studentId}/modify")
    public ModelAndView modifyStudentForm(@ModelAttribute Student student) {

        ModelAndView mav = new ModelAndView("/studentModify");
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/{studentId}/modify")
    public String modifyStudent(@PathVariable("studentId") Long id,
                                @Valid @ModelAttribute StudentRegisterRequest modifiedStudent,
                                BindingResult bindingResult,
                                Model model) {


        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        studentRepository.modify(id, modifiedStudent);

        Student student = studentRepository.getStudent(id);
        model.addAttribute("student", student);
        return "redirect:/student/" + student.getId();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(StudentNotFoundException ex, Model model) {

        model.addAttribute("exception", ex);
        return "error";
    }
}
