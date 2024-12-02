package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a product by ID
    @GetMapping("/product")
    public Optional<Product> getProductById(@RequestParam(value = "productID") Integer productID) {
        return productRepository.findById(productID);
    }

    // Delete a product by ID
    @DeleteMapping("/products/remove")
    public void deleteProduct(@RequestParam(value = "productID") Integer productID) {
        productRepository.deleteById(productID);
    }

    // Search for products by name
    @GetMapping("/products/search")
    public List<Product> searchProductsByName(@RequestParam(value = "name") String name) {
        return productRepository.findByNameContaining(name);
    }
}