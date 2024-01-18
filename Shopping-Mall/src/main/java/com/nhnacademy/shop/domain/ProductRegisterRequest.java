package com.nhnacademy.shop.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class ProductRegisterRequest {

    @NotBlank
    @Length(max = 10)
    String modelNumber;

    @NotBlank
    @Length(max = 120)
    String modelName;

    @NotNull
    Integer unitCost;

    String description;
}
