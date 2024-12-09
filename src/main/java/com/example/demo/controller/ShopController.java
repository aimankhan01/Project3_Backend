package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ShopController {

    private final ShopRepository ShopRepository;

    @Autowired
    public ShopController(ShopRepository ShopRepository) {
        this.ShopRepository = ShopRepository;
    }

    // Get all Shops
    @GetMapping("/shops")
    public List<Shop> getAllShops() {
        return ShopRepository.findAll();
    }

    // Get a Shop by ID
    @GetMapping("/shop")
    public Optional<Shop> getShopById(@RequestParam(value = "shopID") Integer ShopID) {
        return ShopRepository.findById(ShopID);
    }
      // Delete a Shop by ID
      @DeleteMapping("/shops/remove")
      public void deleteShop(@RequestParam(value = "shopID") Integer ShopID) {
          ShopRepository.deleteById(ShopID);
      }
  
      // Search for Shops by name 
      @GetMapping("/shops/search")
      public List<Shop> searchShopsByName(@RequestParam(value = "name") String name) {
          return ShopRepository.findShopByName(name);
      }
}