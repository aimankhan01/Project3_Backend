package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
     @Query("SELECT p " +
            "FROM Products p " +
            "WHERE p.name LIKE CONCAT('%', :search_term, '%')")
    List<Product> findProductsByName(Integer wishlistID);
}
