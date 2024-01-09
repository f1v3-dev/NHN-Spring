package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class Student {

    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Min(0)
    @Max(100)
    private Integer score;

    @Length(max = 200)
    private String comment;

    private Student(String name, String email, Integer score, String comment) {
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }

    public static Student create(String name, String email, Integer score, String comment) {
        return new Student(name, email, score, comment);
    }

    public Student hideScoreStudent() {
        return new Student(this.name, this.email, null, null);
    }
}
