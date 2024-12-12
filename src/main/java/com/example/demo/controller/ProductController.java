package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8082"})
public class ProductController {

    private final ProductRepository ProductRepository;

    @Autowired
    public ProductController(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    // Get all Products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return ProductRepository.findAll();
    }

    // Get a Product by ID
    @GetMapping("/product")
    public Optional<Product> getProductById(@RequestParam(value = "productID") Integer ProductID) {
        return ProductRepository.findById(ProductID);
    }
      // Delete a Product by ID
      @DeleteMapping("/products/remove")
      public void deleteProduct(@RequestParam(value = "productID") Integer ProductID) {
          ProductRepository.deleteById(ProductID);
      }

      // Add a new Product
@PostMapping("/products/add")
public ResponseEntity<String> addProduct(@RequestBody Product product) {
    try {
        Product savedProduct = ProductRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product.");
    }
}
  
      // Search for Products by name 
      @GetMapping("/products/search")
      public List<Product> searchProductsByName(@RequestParam(value = "name") String name) {
          return ProductRepository.findProductsByName(name);
      }

      // Search for Products by shop 
      @GetMapping("/products/search/shop")
      public List<Product> searchProductsByShop(@RequestParam(value = "name")String name) {
          return ProductRepository.findProductsByShop(name);
      }

      // Search for Products by category 
      @GetMapping("/products/search/category")
      public List<Product> searchProductsByCategory(@RequestParam(value = "category") String category) {
          return ProductRepository.findProductsByCategory(category);
      }

      @PutMapping("/items/update")
      public ResponseEntity<String> updateProduct(@RequestParam Integer productID,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Double price,
                                                  @RequestParam(required = false) String category,
                                                  @RequestParam(required = false) String image_link,
                                                  @RequestParam(required = false) int stock,
                                                  @RequestParam(required = false) String description,
                                                  @RequestParam(required = false) Boolean bought) {
  
          Optional<Product> optionalProduct = ProductRepository.findById(productID);
  
          if (optionalProduct.isPresent()) {
              Product product = optionalProduct.get();
  
              if (name != null) product.setName(name);
              if (price != null) product.setPrice(price);
              if (category != null) product.setCategory(category);              
              if (stock != 0) product.setStock(stock);
              if (description != null) product.setDescription(description);              
              ProductRepository.save(product);
              return ResponseEntity.ok("Product updated successfully.");
          } else {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
          }
      }
}