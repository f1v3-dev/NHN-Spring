package com.nhnacademy.shop.domain;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


/**
 * CREATE TABLE IF NOT EXISTS Categories
 * (
 *     category_id   INT auto_increment,
 *     category_name varchar(50) NOT NULL UNIQUE,
 *
 *     CONSTRAINT pk_Categories PRIMARY KEY (category_id)
 * );
 */

@Getter
@Setter
@NoArgsConstructor
public class CategoryRegisterRequest {

    @NotBlank
    @Length(max = 50)
    private String categoryName;
}
