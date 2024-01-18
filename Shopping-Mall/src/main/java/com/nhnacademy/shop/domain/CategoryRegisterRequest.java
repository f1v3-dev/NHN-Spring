package com.nhnacademy.shop.domain;

import javax.validation.constraints.NotBlank;
import lombok.Value;
import org.hibernate.validator.constraints.Length;


@Value
public class CategoryRegisterRequest {

    @NotBlank
    @Length(max = 50)
    String categoryName;
}
