package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;


public class CsvStudents implements Students {

    private final Collection<Student> students;

    private CsvStudents() {
        this.students = new ArrayList<>();
    }


    // TODO 3 : Java Singleton 패턴으로 getInstance() 를 구현하세요.
    private static class CsvStudentsHolder {
        private static final CsvStudents csvStudents = new CsvStudents();
    }

    public static Students getInstance() {
        return CsvStudentsHolder.csvStudents;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
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

    // TODO 8 : students 데이터에 score 정보를 추가하세요.
    @Override
    public void merge(Collection<Score> scores) {
        students.forEach(student -> scores.stream()
                .filter(score -> student.getSeq() == score.getStudentSeq())
                .forEach(student::setScore));
    }
}
