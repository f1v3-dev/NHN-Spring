package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.service.StudentService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.repository.Student;
import java.util.Collection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        DataLoadService csvDataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
        csvDataLoadService.loadAndMerge();

        StudentService studentService = context.getBean("defaultStudentService", StudentService.class);
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);
    }
}
