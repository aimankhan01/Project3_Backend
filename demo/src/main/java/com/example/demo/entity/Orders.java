package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "orders")

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;
    private Integer userID;
    private Integer productID;

    
    // Getters and Setters

    public Integer getUserID() {
        return userID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    
}
