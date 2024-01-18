package com.nhnacademy.shop.domain;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


/**
 * CREATE TABLE IF NOT EXISTS Products
 * (
 *     product_id    INT auto_increment,
 *     model_number  varchar(10)  NOT NULL UNIQUE,
 *     model_name    varchar(120) NOT NULL,
 *     product_image varchar(30),
 *     unit_cost     INT          NOT NULL,
 *     description   text,
 *
 *     CONSTRAINT pk_Products PRIMARY KEY (product_id)
 * );
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductRegisterRequest {

    @NotBlank
    @Length(max = 10)
    String modelNumber;

    @NotBlank
    @Length(max = 120)
    String modelName;

    @NotBlank
    Integer unitCost;

    String description;
}
