package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
     @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.name LIKE CONCAT('%', :search_term, '%')")
    List<Product> findProductsByName(@Param("search_term")String name);

    @Query("SELECT p " +
        "FROM Product p " +
        "WHERE p.shopID = :shopID")
    List<Product> findProductsByShop(Integer shopID);
}