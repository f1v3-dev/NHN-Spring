package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Repository;


@Repository
public class CsvStudents implements Students {

    private final Collection<Student> students = new ArrayList<>();

    @Override
    public void load() {

        ClassLoader loader = CsvStudents.class.getClassLoader();

        try (FileInputStream file = new FileInputStream(loader.getResource("data/student.csv").getFile());
             BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                int seq = Integer.parseInt(split[0]);
                String name = split[1];

                Student student = new Student(seq, name);
                students.add(student);
            }
        } catch (NullPointerException np) {
            np.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    @Override
    public void merge(Collection<Score> scores) {
        students.forEach(student -> scores.stream()
                .filter(score -> student.getSeq() == score.getStudentSeq())
                .forEach(student::setScore));
    }
}
