package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer>{
    @Query("SELECT s " +
            "FROM Shop s " +
            "WHERE s.name LIKE CONCAT('%', :search_term, '%')")
    List<Shop> findShopByName(@Param("search_term") String name);
}
