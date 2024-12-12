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
    private String cartItems; // Updated to store the list of items in the cart as a JSON or serialized string
    private Double total; // Total price of the order

    // Getters and Setters

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getCartItems() {
        return cartItems;
    }

    public void setCartItems(String cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    // Override equals and hashCode based on your needs
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderID, orders.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID);
    }
}