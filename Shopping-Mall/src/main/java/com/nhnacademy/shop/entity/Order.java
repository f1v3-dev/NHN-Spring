package com.nhnacademy.shop.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "ship_date")
    private LocalDateTime shipDate;
}
