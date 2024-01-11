package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Value
public class StudentRegisterRequest {

    @NotBlank
    String name;

    @Email
    String email;

    @Range(min = 0, max = 100)
    Integer score;

    @Length(max = 200)
    String comment;
}
