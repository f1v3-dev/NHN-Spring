package com.nhnacademy.shop.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail {

    @EmbeddedId
    private Pk pk;

    private Integer quantity;

    @Column(name = "unit_cost")
    private Integer unitCost;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Embeddable
    private static class Pk implements Serializable {

        @Column(name = "order_id")
        private Integer orderId;

        @Column(name = "product_id")
        private Integer productId;
    }
}
