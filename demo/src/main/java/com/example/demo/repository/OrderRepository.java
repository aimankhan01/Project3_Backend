package com.example.demo.repository;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query("SELECT o FROM Orders o WHERE o.customerName LIKE CONCAT('%', :customerName, '%')")
    List<Orders> findByCustomerNameContaining(@Param("customerName") String customerName);

    // Find orders by userID
    List<Orders> findByUserID(Integer userID);

    // Find orders by productID
    List<Orders> findByProductID(Integer productID);
}
