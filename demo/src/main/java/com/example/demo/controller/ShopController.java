package com.example.demo.controller;

import com.example.demo.entity.Shop;
import com.example.demo.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShopController {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    // Get all shops
    @GetMapping("/shops")
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // Get a shop by ID
    @GetMapping("/shop")
    public Optional<Shop> getShopById(@RequestParam(value = "shopID") Integer shopID) {
        return shopRepository.findById(shopID);
    }

    // Delete a shop by ID
    @DeleteMapping("/shops/remove")
    public void deleteShop(@RequestParam(value = "shopID") Integer shopID) {
        shopRepository.deleteById(shopID);
    }

    // Search for shops by name
    @GetMapping("/shops/search")
    public List<Shop> searchShopsByName(@RequestParam(value = "name") String name) {
        return shopRepository.findByNameContaining(name);
    }
}