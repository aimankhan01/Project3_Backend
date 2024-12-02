package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Method to find products by name (case-insensitive)
    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%')")
    List<Product> findByNameContaining(@Param("name") String name);

    // Optional: Finds a product by its ID
    Optional<Product> findById(Integer productID);

    // Optional: Finds a product by its name (exact match)
    Optional<Product> findByName(String name);
}
