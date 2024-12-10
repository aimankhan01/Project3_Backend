package com.example.demo.repository;

import com.example.demo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    // Method to find shops by name (case-insensitive)
    @Query("SELECT s FROM Shop s WHERE s.name LIKE CONCAT('%', :name, '%')")
    List<Shop> findByNameContaining(@Param("name") String name);

    // Optional: Finds a shop by its ID
    Optional<Shop> findById(Integer shopID);

    // Optional: Finds a shop by its name (exact match)
    Optional<Shop> findByName(String name);
}
