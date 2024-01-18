package com.nhnacademy.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Setter
    @Column(name = "model_number")
    private String modelNumber;

    @Setter
    @Column(name = "model_name")
    private String modelName;

    @Setter
    @Column(name = "product_image")
    private String productImage;

    @Setter
    @Column(name = "unit_cost")
    private Integer unitCost;

    @Setter
    private String description;
}
