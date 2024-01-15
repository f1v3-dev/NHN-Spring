package com.nhnacademy.shop.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Products_Categories")
public class ProductCategory {

    @EmbeddedId
    private Pk pk;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    private static class Pk implements Serializable {

        @Column(name = "product_id")
        private Integer productId;

        @Column(name = "category_id")
        private Integer categoryId;
    }
}

