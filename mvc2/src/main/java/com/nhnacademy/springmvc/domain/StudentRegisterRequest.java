package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@NoArgsConstructor(force = true)
public class StudentRegisterRequest {

    @NotBlank
    String name;

    @Email
    String email;

    @Min(0)
    @Max(100)
    Integer score;

    @Length(max = 200)
    String comment;
}
